package tn.esprit.piproject.Services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class IProjectImp implements IProjectService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private DocumentsRepository documentsRepository;
    @Autowired
    private DefenseRepository defenceRepository ;
    @Autowired
    private EvaluationRepository evaluationRepository ;
    @Autowired
    private HistoriqueDefenseRepository historiqueDefenseRepository ;
    @Autowired
    private RoleRepository roleRepository ;
    private Set<String> stopWords = new HashSet<>();
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void createTextIndexForHistorique() {
        mongoTemplate.indexOps(HistoriqueDefense.class)
                .ensureIndex(new TextIndexDefinition.TextIndexDefinitionBuilder()
                        .onField("dateDefense")
                        .onField("UserStudent")
                        .build());
    }

   /* private String cleanText(String text) {
        return text.toLowerCase()
                .replaceAll("\\p{Punct}", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }*/
    private String cleanText(String text) {
        return text.toLowerCase()
                .replaceAll("\\p{Punct}", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }


    public List<HistoriqueDefense> searchHistoriques(String query) {
        Set<String> queryWords = prepareQuery(query);
        String refinedQuery = String.join(" ", queryWords);

        List<HistoriqueDefense> initialResults = performMongoDBTextSearch(refinedQuery);

        return initialResults;
    }

    /*private Set<String> prepareQuery(String query) {
        Set<String> queryWords = Arrays.stream(cleanText(query).split("\\s+"))
                .filter(word -> !stopWords.contains(word))
                .collect(Collectors.toSet());
        return queryWords;
    }*/
    private Set<String> prepareQuery(String query) {
        Set<String> queryWords = Arrays.stream(cleanText(query).split("\\s+"))
                .filter(word -> !stopWords.contains(word))
                .collect(Collectors.toSet());
        return queryWords;
    }

    /*private List<HistoriqueDefense> performMongoDBTextSearch(String query) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(query);
        Query queryMongo = TextQuery.queryText(criteria).sortByScore().limit(10);
        return mongoTemplate.find(queryMongo, HistoriqueDefense.class);
    }*/
    private List<HistoriqueDefense> performMongoDBTextSearch(String query) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(query);
        Query queryMongo = TextQuery.queryText(criteria).sortByScore().limit(10);
        return mongoTemplate.find(queryMongo, HistoriqueDefense.class);
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
    public List<User> getUsersByRole(ERole role) {
        return userRepository.findByERole(role);
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

    @Scheduled(fixedRate = 86400000) // Vérifie chaque jour
    @Override
    public void transferOldDefensesToHistory() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.minusDays(7); // Date limite il y a 7 jours

        List<Defense> oldDefenses = defenceRepository.findByDateDefenseBefore(threshold);

        for (Defense defense : oldDefenses) {
            HistoriqueDefense historiqueDefense = new HistoriqueDefense(defense);
            historiqueDefenseRepository.save(historiqueDefense);
            defenceRepository.delete(defense);
        }
    }
    /*@Scheduled(fixedRate = 86400000) // Vérifie chaque jour
    public void transferOldDefensesToHistory() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.minusDays(7); // Date limite il y a 7 jours

        List<Defense> oldDefenses = defenceRepository.findByDateDefenseBefore(threshold);

        for (Defense defense : oldDefenses) {
            HistoriqueDefense historiqueDefense = new HistoriqueDefense(defense);
            historiqueDefenseRepository.save(historiqueDefense);
            defenceRepository.delete(defense);
        }
    }*/


    @Override
    public Optional<HistoriqueDefense> gethistoriqueDefenceByIdById(int id) {
        return historiqueDefenseRepository.findById(id);    }

//************************************************************************/////////////

    @Override
    public List<Defense> getAllDefence() {
        return defenceRepository.findAll() ;
    }

    @Override
    public Optional<Defense> getDefenceById(int id) {
        return defenceRepository.findById(id) ;
    }

    @Override
    public Defense createDefence(Defense defence) {

      /// defence.generateAttributes();
        return  defenceRepository.save(defence) ;
    }

    @Override
    public Defense updateDefence(Defense defence) {
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
        Defense defense = defenceRepository.findById(defenseId)
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

    @Override
    public List<HistoriqueDefense> getAllHistoriqueDefense() {
        return historiqueDefenseRepository.findAll() ;
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
    public List<Defense> getAllDefenses() {
        return defenceRepository.findAll();

    }

    @Override
    public List<User> getAllUserss() {
        return userRepository.findAll();
    }


/****************************************----------Defence---------****************************************/
   /* @Override
    public List<Defence> getAllDefence() {
        return defenceRepository.findAll() ;
    }

    @Override
    public Optional<Defence> getDefenceById(int id) {
        return defenceRepository.findById(id);
    }

    @Override
    public Defence createDefence(Defence defence) {
        return  defenceRepository.save(defence) ;
    }

    @Override
    public Defence updateDefence(Defence defence) {
        return defenceRepository.save(defence) ;
    }

    @Override
    public void deleteDefence(int id) {
        defenceRepository.deleteById(id);
    }*/
    /****************************************----------EVALUTION---------****************************************/
/*
    @Override
    public List<Evaluation> getAllEvalution() {
        return evaluationRepository.findAll() ;
    }

    @Override
    public Optional<Evaluation> getEvalutioneById(int id) {
        return evaluationRepository.findById(id);    }

    @Override
    public Evaluation createEvalution(Evaluation evaluation)
    {

        return  evaluationRepository.save(evaluation) ;
    }

    @Override
    public Evaluation updateEvalution(Evaluation evaluation) {
        return evaluationRepository.save(evaluation) ;

    }

    @Override
    public void deleteEvalution(int id) {
        evaluationRepository.deleteById(id);

    }
*/
    /***************************************************/
    /*
    @Override
    public List<Documents> getAllDocuments() {
        return documentsRepository.findAll();
    }

    @Override
    public Optional<Documents> getDocumentsById(int id) {
        return DocumentsRepository.findById(id);
    }

    @Override
    public User createDocument(Documents documents) {
        return documentsRepository.save(documents);
    }

    @Override
    public User updateDocuments(Documents documents) {
        return userRepository.save(documents);
    }

    @Override
    public void deleteDocuments(int id) {
        documentsRepository.deleteById(id);
    }*/
}
