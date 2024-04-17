package tn.esprit.piproject.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Monitoring;

@Repository
public interface TaskMonitoringRepository extends MongoRepository<Monitoring, Integer> {}
