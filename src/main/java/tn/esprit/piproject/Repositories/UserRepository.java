package tn.esprit.piproject.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findUserByname(String name);

    Boolean existsByname(String name);

    Optional<User> findFirstByEmail(String email);
}
