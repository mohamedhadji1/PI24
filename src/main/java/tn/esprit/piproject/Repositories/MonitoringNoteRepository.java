package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.MonitoringNote;

@Repository
public interface MonitoringNoteRepository extends MongoRepository<MonitoringNote, Integer> {
    // Add custom query methods if needed
}