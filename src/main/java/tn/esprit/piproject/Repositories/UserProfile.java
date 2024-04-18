package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfile extends MongoRepository<UserProfile ,String> {
}
