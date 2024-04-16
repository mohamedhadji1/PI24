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
    @Autowired
    private MonitoringNoteRepository monitoringNoteRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private  RequestRepository requestRepository;
    public IProjectImp (SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
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
    public List<Task> searchTasksByDescription(String keyword) {
        return taskRepository.findByTaskDescriptionContainingIgnoreCase(keyword);
    }

    @Override
    public List<Task> searchTasksByProgress(String keyword) {
        return taskRepository.findByProgressContainingIgnoreCase(keyword);
    }

    @Override
    public List<Task> searchTasksByDuration(String keyword) {
        return taskRepository.findByDurationContainingIgnoreCase(keyword);
    }

    @Override
    public List<Task> searchTasksBySupervisorName(String keyword) {
        return taskRepository.findBySupervisorNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Task> searchTasksByStudentName(String keyword) {
        return taskRepository.findByStudentNameContainingIgnoreCase(keyword);
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
    public Company updatecompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deletecompany(int idComp) {
        companyRepository.deleteById(idComp);
    }

    @Override
    public List<MonitoringNote> getAllMonitoringNotes() {
        return monitoringNoteRepository.findAll();
    }

    @Override
    public Optional<MonitoringNote> getMonitoringNoteById(int id) {
        return monitoringNoteRepository.findById(id);
    }

    @Override
    public MonitoringNote createMonitoringNote(MonitoringNote monitoringNote) {
        return monitoringNoteRepository.save(monitoringNote);
    }
    @Override
    public MonitoringNote updateMonitoringNote(MonitoringNote monitoringNote) {
        return monitoringNoteRepository.save(monitoringNote);
    }
    @Override
    public void deleteMonitoringNoteById(int id) {
        monitoringNoteRepository.deleteById(id);
    }

    @Override
    public List<MonitoringNote> getMonitoringNotesByStatus(Status status) {
        return monitoringNoteRepository.findByStatus(status);
    }


    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
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
        Company company_from_db = companyRepository.findById(offer.getCompany().getId()).orElseGet(null);
        if (company_from_db == null) return Offer.Empty();
        company_from_db.getOffers().add(offer);
        companyRepository.save(company_from_db);
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateoffer(Offer offer) {
        Company company_from_db = companyRepository.findById(offer.getCompany().getId()).orElseGet(null);
        if (company_from_db == null) return Offer.Empty();
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
    public List<Request> getallrequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request createrequest(Request request) {
        request.setId(sequenceGeneratorService.generateSequence("documents_sequence"));
        return requestRepository.save(request);
    }
}

