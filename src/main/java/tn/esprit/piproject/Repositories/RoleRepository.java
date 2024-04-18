package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import tn.esprit.piproject.Entities.ERole;
import tn.esprit.piproject.Entities.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Integer> {
	  Optional<Role> findByName(ERole name);
}
