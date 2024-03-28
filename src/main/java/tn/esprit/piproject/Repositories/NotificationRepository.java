package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Notification;
import tn.esprit.piproject.Entities.NotificationStatus;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Integer> {
    int countByStatus(NotificationStatus status);
}