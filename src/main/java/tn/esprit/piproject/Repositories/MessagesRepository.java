package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.piproject.Entities.ChatMessage;

public interface MessagesRepository  extends MongoRepository<ChatMessage, Integer> {
}
