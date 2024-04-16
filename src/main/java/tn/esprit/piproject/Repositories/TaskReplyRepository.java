package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.piproject.Entities.TaskReply;

public interface TaskReplyRepository extends MongoRepository<TaskReply, Integer> {}
