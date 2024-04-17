package tn.esprit.piproject.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.*;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
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
    @Autowired
    private DefenceRepository defenceRepository ;
    @Autowired
    private EvaluationRepository evaluationRepository ;
    @Autowired
    private HistoriqueDefenseRepository historiqueDefenseRepository ;
    @Autowired
    private MongoTemplate mongoTemplate;
    private Set<String> stopWords = new HashSet<>();
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

    @Scheduled(fixedRate = 86400000) // Vérifie chaque jour
    @Override
    public void transferOldDefensesToHistory() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.minusDays(7); // Date limite il y a 7 jours

        List<Defence> oldDefenses = defenceRepository.findByDateDefenseBefore(threshold);

        for (Defence defense : oldDefenses) {
            HistoriqueDefense historiqueDefense = new HistoriqueDefense(defense);
            historiqueDefenseRepository.save(historiqueDefense);
            defenceRepository.delete(defense);
        }
    }

    @Override
    public Optional<HistoriqueDefense> gethistoriqueDefenceByIdById(int id) {
        return historiqueDefenseRepository.findById(id);    }


    /***************************Defense ********************/
@Override
public List<Defence> getAllDefence() {
    return defenceRepository.findAll() ;
}
    @Override
    public Optional<Defence> getDefenceById(int id) {
        return defenceRepository.findById(id) ;
    }

    @Override
    public Defence createDefence(Defence defence) {

        /// defence.generateAttributes();
        return  defenceRepository.save(defence) ;
    }

    @Override
    public Defence updateDefence(Defence defence) {
        return defenceRepository.save(defence) ;
    }

    @Override
    public void deleteDefence(int id) {
        defenceRepository.deleteById(id);
    }

    @Override
    public List<Evaluation> getAllEvalution() {
        return evaluationRepository.findAll() ;
    }

    @Override
    public Optional<Evaluation> getEvalutioneById(int id) {
        return evaluationRepository.findById(id);    }


    @Override
    public Evaluation createEvalution(Evaluation evaluation) {
        Evaluation newEvaluation = evaluationRepository.save(evaluation);
        createEvaluationWithHistory(newEvaluation.getDefense().getIdDef(), newEvaluation);
        return newEvaluation;
    }
    @Transactional
    public void createEvaluationWithHistory(int defenseId, Evaluation evaluation) {
        Defence defense = defenceRepository.findById(defenseId)
                .orElseThrow(() -> new RuntimeException("Defense not found"));

        // Créer une nouvelle instance d'historique de défense avec les données de la défense
        HistoriqueDefense historiqueDefense = new HistoriqueDefense(defense);

        // Enregistrer l'historique de défense avant de supprimer la défense
        historiqueDefenseRepository.save(historiqueDefense);

        // Enregistrer l'évaluation
        evaluationRepository.save(evaluation);

        // Supprimer la défense
        defenceRepository.delete(defense);
    }
    @PostConstruct
    public void createTextIndexForHistorique() {
        try {
            mongoTemplate.indexOps(HistoriqueDefense.class)
                    .dropIndex("dateDefense_text_UserStudent_text"); // Supprimer l'index existant
        } catch (Exception e) {
            // Gérer l'erreur si l'index n'existe pas ou s'il y a une autre erreur
        }

        // Créer le nouvel index
        try {
            mongoTemplate.indexOps(HistoriqueDefense.class)
                    .ensureIndex(new TextIndexDefinition.TextIndexDefinitionBuilder()
                            .onField("numeroDeBloc")
                            .onField("UserStudent")
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create text index for HistoriqueDefense collection", e);
        }
    }
    @Override
    public List<HistoriqueDefense> getAllHistoriqueDefense() {
        return historiqueDefenseRepository.findAll() ;
    }

    @Override
    public List<HistoriqueDefense> searchHistoriques(String query) {
        Set<String> queryWords = prepareQuery(query);
        String refinedQuery = String.join(" ", queryWords);

        List<HistoriqueDefense> initialResults = performMongoDBTextSearch(refinedQuery);

        return initialResults;
    }
    private String cleanText(String text) {
        return text.toLowerCase()
                .replaceAll("\\p{Punct}", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private Set<String> prepareQuery(String query) {
        Set<String> queryWords = Arrays.stream(cleanText(query).split("\\s+"))
                .filter(word -> !stopWords.contains(word.toLowerCase())) // Ignore les mots vides
                .collect(Collectors.toSet());
        return queryWords;
    }

    private List<HistoriqueDefense> performMongoDBTextSearch(String query) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(query);
        Query queryMongo = TextQuery.queryText(criteria).sortByScore().limit(10);
        return mongoTemplate.find(queryMongo, HistoriqueDefense.class);
    }
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }


    @Override
    public Evaluation updateEvalution(Evaluation evaluation) {
        return evaluationRepository.save(evaluation) ;

    }

    @Override
    public void deleteEvalution(int id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public List<Defence> getAllDefenses() {
        return defenceRepository.findAll();

    }

    @Override
    public List<User> getAllUserss() {
        return userRepository.findAll();
    }












}