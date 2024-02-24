package tn.esprit.piproject.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Evaluation;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, ObjectId> {}
