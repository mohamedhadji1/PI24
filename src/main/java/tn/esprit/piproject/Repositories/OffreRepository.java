package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Offer;

@Repository
public interface OffreRepository extends MongoRepository<Offer, Integer> {}
