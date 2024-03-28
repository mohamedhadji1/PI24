package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.piproject.Entities.Company;
import tn.esprit.piproject.Entities.Request;

public interface RequestRepository extends MongoRepository <Request, Integer> {}

