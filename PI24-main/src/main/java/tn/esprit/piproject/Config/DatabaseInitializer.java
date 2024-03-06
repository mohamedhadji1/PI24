package tn.esprit.piproject.Config;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final DefenseRepository defenceRepository;
    private final DocumentsRepository documentsRepository;
    private final EvaluationRepository evaluationRepository;
 private final HistoriqueDefenseRepository historiqueDefenseRepository  ;
    private final OffreRepository offreRepository;
    private final RoleRepository roleRepository;
    private final ResponseRepository responseRepository;

    private final TaskRepository taskRepository ;
    public DatabaseInitializer(UserRepository userRepository,
                               CompanyRepository companyRepository, ComplaintRepository complaintRepository,
                               DefenseRepository defenceRepository,
                               DocumentsRepository documentsRepository, EvaluationRepository evaluationRepository
            , InternshipRepository internshipRepository, OffreRepository offreRepository, ResponseRepository responseRepository, TaskRepository taskRepository , HistoriqueDefenseRepository historiqueDefenseRepository , RoleRepository roleRepository) {
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
this.historiqueDefenseRepository=historiqueDefenseRepository ;
        this.roleRepository = roleRepository;
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
            Defense defence = new Defense();
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
        if (roleRepository.count() == 0) {
            Role role = new Role();
            roleRepository.save(role);
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
        if (historiqueDefenseRepository.count() == 0) {
            HistoriqueDefense historiqueDefense = new HistoriqueDefense();
            historiqueDefenseRepository.save(historiqueDefense);
        }
    }
}