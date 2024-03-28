package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.ChatMessage;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, Integer> {
}