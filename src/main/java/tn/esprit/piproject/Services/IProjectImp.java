package tn.esprit.piproject.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.*;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class IProjectImp implements IProjectService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private DocumentsRepository documentsRepository;
    @Autowired
    private TaskMonitoringRepository taskMonitoringRepository;
    @Autowired
    private OffreRepository offerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private TurnInRepository turnInRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private NotificationRepository notificationRepository;
    public IProjectImp (SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void sendNotification(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notification", notification);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    /*********************************************************/
    @Override
    public List<Internship> getAllinternships() {
        return internshipRepository.findAll();
    }

    @Override
    public Optional<Internship> getinternshipById(int id) {
        return internshipRepository.findById(id);
    }

    @Override
    public Internship createInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public Internship updateinternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public void deleteinternship(int id) {
        internshipRepository.deleteById(id);
    }

    @Override
    public List<Documents> getAlldocuments() {
        return documentsRepository.findAll();
    }

    @Override
    public Optional<Documents> getdocumentsById(int id) {
        return documentsRepository.findById(id);
    }

    @Override
    public Documents createdocuments(Documents documents) {
        return documentsRepository.save(documents);
    }

    @Override
    public Documents updatedocuments(Documents documents) {
        return documentsRepository.save(documents);
    }

    @Override
    public void deletedocuments(int id) {
        documentsRepository.deleteById(id);

    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }


    @Override
    public String getAttachmentFilename(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return task.getAttachmentFileName();
        }
        return null;
    }

    @Override
    public Resource downloadTaskAttachment(int taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (task.getAttachmentData() != null && task.getAttachmentFileName() != null) {
                ByteArrayResource resource = new ByteArrayResource(task.getAttachmentData());
                resource.getFilename();

                return resource;
            }
        }
        return null;
    }

    @Override
    public List<Company> getAllcompany() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(int idComp) {
        return companyRepository.findById(idComp);
    }

    @Override
    public Company updatecompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deletecompany(int idComp) {
        companyRepository.deleteById(idComp);
    }

    @Override
    public List<MonitoringNote> getAllMonitoringNotes() {
        return null;
    }

    @Override
    public Optional<MonitoringNote> getMonitoringNoteById(String id) {
        return Optional.empty();
    }

    @Override
    public MonitoringNote createMonitoringNote(MonitoringNote monitoringNote) {
        return null;
    }

    @Override
    public MonitoringNote updateMonitoringNote(MonitoringNote monitoringNote) {
        return null;
    }

    @Override
    public void deleteMonitoringNoteById(String id) {

    }

    @Override
    public List<Notification> getAllNotifications() {
        return null;
    }

    @Override
    public Optional<Notification> getNotificationById(String id) {
        return Optional.empty();
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return null;
    }

    @Override
    public void deleteNotificationById(String id) {

    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    @Override
    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    @Override
    public List<ChatMessage> getMessagesBetweenSupervisorAndStudent(int supervisorId, int studentId) {
        return chatMessageRepository.findBySender_IdAndRecipient_Id(supervisorId, studentId);

    }

    @Override
    public TurnIn submitTurnIn(TurnIn turnIn) {
        return turnInRepository.save(turnIn);
    }

    @Override
    public List<TurnIn> getAllTurnIns() {
        return turnInRepository.findAll();
    }

    @Override
    public TurnIn getTurnInById(int turnInId) {
        Optional<TurnIn> turnIn = turnInRepository.findById(turnInId);
        return turnIn.orElse(null);
    }

    @Override
    public List<TurnIn> getTurnInsByStudentId(int studentId) {
        return turnInRepository.findByStudentId(studentId);
    }
}

