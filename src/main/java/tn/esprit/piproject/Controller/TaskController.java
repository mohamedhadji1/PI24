package tn.esprit.piproject.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.Notification;
import tn.esprit.piproject.Entities.Task;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Repositories.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.piproject.Services.IProjectService;

import javax.annotation.Resource;
import java.io.IOException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")

public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AutoIncrementUtil autoIncrementUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IProjectService iProjectService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam("file") MultipartFile file, @RequestParam("task") String taskJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Task task = objectMapper.readValue(taskJson, Task.class);
            if (!file.isEmpty()) {
                task.setAttachmentFileName(file.getOriginalFilename());
                task.setAttachmentData(file.getBytes());
            }
            User supervisor = userRepository.findById(task.getSupervisor().getId()).orElse(null);
            User student = userRepository.findById(task.getStudent().getId()).orElse(null);
            if (supervisor == null || student == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            task.setSupervisor(supervisor);
            task.setStudent(student);
            int id = autoIncrementUtil.getNextSequence("votre_sequence");
            task.setId(id);
            Task createdTask = taskRepository.save(task);
            //iProjectService.sendAssignmentNotification(createdTask);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") int taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable("taskId") int taskId, @RequestBody Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask != null) {
            updatedTask.setId(taskId);
            Task savedTask = taskRepository.save(updatedTask);
            if (savedTask.getProgress().equals("completed")) {
                iProjectService.sendTaskCompletionNotification(savedTask);
            }
            return new ResponseEntity<>(savedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") int taskId) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask != null) {
            taskRepository.deleteById(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{taskId}/attachment/download")
    public ResponseEntity<org.springframework.core.io.Resource> downloadTaskAttachment(@PathVariable int taskId) {
        org.springframework.core.io.Resource resource = iProjectService.downloadTaskAttachment(taskId);
        if (resource != null) {
            String filename = iProjectService.getAttachmentFilename(taskId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/notifications/count")
    public ResponseEntity<Integer> getUnreadNotificationCount() {
        int unreadCount = iProjectService.getUnreadNotificationCount();
        return ResponseEntity.ok(unreadCount);
    }
}
