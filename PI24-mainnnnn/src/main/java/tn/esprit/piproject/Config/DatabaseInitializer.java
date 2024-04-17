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
    private final HistoriqueDefenseRepository historiqueDefenseRepository ;
    private final OfferRepository offerRepository;
    private final ResponseRepository responseRepository;
    private final TaskRepository taskRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final NotificationRepository notificationRepository;
    private final TurnInRepository turnInRepository;
    private final MonitoringNoteRepository monitoringNoteRepository;
    private final RequestRepository requestRepository;
    public DatabaseInitializer(
                               UserRepository userRepository,
                               CompanyRepository companyRepository,
                               ComplaintRepository complaintRepository,
                               DefenceRepository defenceRepository,
                               DocumentsRepository documentsRepository,
                               EvaluationRepository evaluationRepository,
                               InternshipRepository internshipRepository,
                               OfferRepository offerRepository,
                               ResponseRepository responseRepository,
                               TaskRepository taskRepository,
                               ChatMessageRepository chatMessageRepository,
                               NotificationRepository notificationRepository,
                               TurnInRepository turnInRepository,
                               MonitoringNoteRepository monitoringNoteRepository,
                               RequestRepository requestRepository ,HistoriqueDefenseRepository historiqueDefenseRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.complaintRepository=  complaintRepository;
        this.defenceRepository= defenceRepository;
        this.documentsRepository=documentsRepository;
        this.evaluationRepository=evaluationRepository;
        this.internshipRepository=internshipRepository;
        this.offerRepository = offerRepository;
        this.responseRepository=responseRepository;
        this.taskRepository=taskRepository;
        this.chatMessageRepository=chatMessageRepository;
        this.notificationRepository=notificationRepository;
        this.turnInRepository=turnInRepository;
        this.monitoringNoteRepository=monitoringNoteRepository;
        this.requestRepository = requestRepository;
        this.historiqueDefenseRepository=historiqueDefenseRepository ;
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
        if (historiqueDefenseRepository.count() == 0) {
            HistoriqueDefense historiqueDefense = new HistoriqueDefense();
            historiqueDefenseRepository.save(historiqueDefense);
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

        if (offerRepository.count() == 0) {
            Offer offre = new Offer();
            offerRepository.save(offre);
        }

        if (responseRepository.count() == 0) {
            Response response = new Response();
            responseRepository.save(response);
        }

        if (taskRepository.count() == 0) {
            Task task = new Task();
            taskRepository.save(task);
        }if (chatMessageRepository.count() == 0) {
            ChatMessage chatrepo = new ChatMessage();
            chatMessageRepository.save(chatrepo);
        }if (notificationRepository.count() == 0) {
            Notification notification = new Notification();
            notificationRepository.save(notification);
        }if (turnInRepository.count() == 0) {
            TurnIn turnin = new TurnIn();
            turnInRepository.save(turnin);
        }
        if (monitoringNoteRepository.count() == 0) {
            MonitoringNote notes = new MonitoringNote();
            monitoringNoteRepository.save(notes);
        }
        if (requestRepository.count() == 0) {
            Request request = new Request();
            requestRepository.save(request);
        }
    }
}