package tn.esprit.piproject.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Intership;

@Repository
public interface InternshipRepository extends MongoRepository<Intership, Integer> {}

