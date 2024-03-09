package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.config.MongoDbFactoryParser;
import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.piproject.Entities.Message;

public interface MessagesRepository  extends MongoRepository<Message, Integer> {
}
