package tn.esprit.piproject.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.*;

import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private SequenceGeneratorService sequenceGeneratorService;

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
    public List<Task> getAllTasks() {return taskRepository.findAll();}

    @Override
    public Optional<Task> getTaskById(int id) {return taskRepository.findById(id);}

    @Override
    public Task createTask(Task task) {return taskRepository.save(task);}

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
    @Override
    public Monitoring createTaskMonitoring(Monitoring monitoring) {
        return taskMonitoringRepository.save(monitoring);
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
    public String getAttachmentFilename(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return task.getAttachmentFileName();
        }
        return null;
    }
    @Override
    public List<Offer> getAllOffer() {
        return offerRepository.findAll();
    }


    @Override
    public Optional<Offer> getofferById(int id) {
        return offerRepository.findById(id);
    }

    public List<Offer> getoffersByCompany(int id) {
        List<Offer> all_offers = offerRepository.findAll();
        return all_offers.stream()
                .filter(offer -> offer.getCompany().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public Offer createoffer(Offer offer) {
        offer.setId(sequenceGeneratorService.generateSequence("documents_sequence"));
        Date currentDate = Date.from(Instant.now());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3);
        Date date_after_3_days = (Date) c.getTime();
        offer.setDateStart(currentDate);
        offer.setDateEnd(date_after_3_days);
        Company company_from_db=companyRepository.findById(offer.getCompany().getId()).orElseGet(null);
        if(company_from_db == null) return Offer.Empty();
        company_from_db.getOffers().add(offer);
        companyRepository.save(company_from_db);
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateoffer(Offer offer) {
        Company company_from_db=companyRepository.findById(offer.getCompany().getId()).orElseGet(null);
        if(company_from_db == null) return Offer.Empty();
        company_from_db.getOffers().add(offer);
        companyRepository.save(company_from_db);
        return offerRepository.save(offer);
    }

    @Override
    public void deleteoffer(int id) {
        offerRepository.findById(id).ifPresent(offer_value -> {
            companyRepository.findById(offer_value.getCompany().getId()).ifPresent(company_value -> {
                company_value.getOffers().remove(offer_value);
                companyRepository.save(company_value);
            });
            offerRepository.deleteById(offer_value.getId());
        });
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
    public Company createcompany(Company company) {
        company.setId(sequenceGeneratorService.generateSequence("documents_sequence"));
        return companyRepository.save(company);
    }


    @Override
    public Company updatecompany(Company company) {return companyRepository.save(company);
    }

    @Override
    public void deletecompany(int idComp) {
        companyRepository.deleteById(idComp);
    }

}

