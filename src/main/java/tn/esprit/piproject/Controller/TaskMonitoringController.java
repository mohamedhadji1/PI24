package tn.esprit.piproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.piproject.Entities.Monitoring;
import tn.esprit.piproject.Entities.Task;
import tn.esprit.piproject.Repositories.TaskRepository;
import tn.esprit.piproject.Services.IProjectService;
import tn.esprit.piproject.Config.AutoIncrementUtil;
@RestController
@RequestMapping("/api/monitoring")
public class TaskMonitoringController {

    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private AutoIncrementUtil autoIncrementUtil;
    @Autowired
    private TaskRepository taskRepository;
}
