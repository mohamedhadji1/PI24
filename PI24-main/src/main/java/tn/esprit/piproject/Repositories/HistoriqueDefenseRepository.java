package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Evaluation;
import tn.esprit.piproject.Entities.HistoriqueDefense;
@Repository

public interface HistoriqueDefenseRepository extends MongoRepository<HistoriqueDefense, Integer> {

}
