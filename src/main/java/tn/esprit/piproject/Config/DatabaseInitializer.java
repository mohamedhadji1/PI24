package tn.esprit.piproject.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.*;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final InternshipRepository internshipRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ComplaintRepository complaintRepository;
    private final DefenceRepository defenceRepository;
    private final DocumentsRepository documentsRepository;
    private final EvaluationRepository evaluationRepository;

    private final OffreRepository offreRepository;
    private final ResponseRepository responseRepository;
    private final TaskRepository taskRepository;
    private final TaskMonitoringRepository taskMonitoringRepository;
    private final ChatMessageRepository chatMessageRepository;
    public DatabaseInitializer(UserRepository userRepository,
                               CompanyRepository companyRepository, ComplaintRepository complaintRepository,
                               DefenceRepository defenceRepository,
                               DocumentsRepository documentsRepository,EvaluationRepository evaluationRepository
            , InternshipRepository internshipRepository,OffreRepository offreRepository,ResponseRepository responseRepository,TaskRepository taskRepository,TaskMonitoringRepository taskMonitoringRepository,ChatMessageRepository chatMessageRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.complaintRepository=  complaintRepository;
        this.defenceRepository= defenceRepository;
        this.documentsRepository=documentsRepository;
        this.evaluationRepository=evaluationRepository;
        this.internshipRepository=internshipRepository;
        this.offreRepository=offreRepository;
        this.responseRepository=responseRepository;
        this.taskRepository=taskRepository;
        this.taskMonitoringRepository=taskMonitoringRepository;
        this.chatMessageRepository=chatMessageRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User();
            userRepository.save(user);
        }

        if (companyRepository.count() == 0) {
            Company company = new Company();
            companyRepository.save(company);
        }

        if (complaintRepository.count() == 0) {
            Complaint complaint = new Complaint();
            complaintRepository.save(complaint);
        }

        if (defenceRepository.count() == 0) {
            Defence defence = new Defence();
            defenceRepository.save(defence);
        }

        if (documentsRepository.count() == 0) {
            Documents document = new Documents();
            documentsRepository.save(document);
        }

        if (evaluationRepository.count() == 0) {
            Evaluation evaluation = new Evaluation();
            evaluationRepository.save(evaluation);
        }

        if (internshipRepository.count() == 0) {
            Internship internship = new Internship();
            internshipRepository.save(internship);
        }

        if (offreRepository.count() == 0) {
            Offre offre = new Offre();
            offreRepository.save(offre);
        }

        if (responseRepository.count() == 0) {
            Response response = new Response();
            responseRepository.save(response);
        }

        if (taskRepository.count() == 0) {
            Task task = new Task();
            taskRepository.save(task);
        }
        if (taskMonitoringRepository.count() == 0) {
            Monitoring MonitoringRepository = new Monitoring();
            taskMonitoringRepository.save(MonitoringRepository);
        }if (chatMessageRepository.count() == 0) {
            ChatMessage chatrepo = new ChatMessage();
            chatMessageRepository.save(chatrepo);
        }
    }
}