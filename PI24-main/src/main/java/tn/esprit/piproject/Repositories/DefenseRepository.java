package tn.esprit.piproject.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Defense;

@Repository
public interface DefenseRepository extends MongoRepository<Defense, Integer> {}