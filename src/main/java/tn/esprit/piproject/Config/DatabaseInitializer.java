package tn.esprit.piproject.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.*;

import javax.swing.text.Document;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ComplaintRepository complaintRepository;
    private final DefenceRepository defenceRepository;
    private final DocumentsRepository documentsRepository;
    private final EvaluationRepository evaluationRepository;
    private final InternshipRepository internshipRepository;
    private final OffreRepository offreRepository;
    private final ResponseRepository responseRepository;
    private final TaskRepository taskRepository;

    public DatabaseInitializer(UserRepository userRepository,
                               CompanyRepository companyRepository, ComplaintRepository complaintRepository,
                               DefenceRepository defenceRepository,
                               DocumentsRepository documentsRepository,EvaluationRepository evaluationRepository
    ,InternshipRepository internshipRepository,OffreRepository offreRepository,ResponseRepository responseRepository,TaskRepository taskRepository) {
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
    }

    @Override
    public void run(String... args) {
        User user = new User();
        Company company = new Company();
        Complaint complaint=new Complaint();
        Defence defence=new Defence();
       Documents document=new Documents();
       Evaluation evaluation=new Evaluation();
       Intership intership=new Intership();
       Offre offre=new Offre();
       Response response=new Response();
       Task task=new Task();
        userRepository.save(user);
        companyRepository.save(company);
        complaintRepository.save(complaint);
        defenceRepository.save(defence);
        documentsRepository.save(document);
        evaluationRepository.save(evaluation);
        internshipRepository.save(intership);
        offreRepository.save(offre);
        responseRepository.save(response);
        taskRepository.save(task);


    }
}
