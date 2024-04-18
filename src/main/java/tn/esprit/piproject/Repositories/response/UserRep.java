package tn.esprit.piproject.Repositories.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.ERole;
import tn.esprit.piproject.Entities.Role;
import tn.esprit.piproject.Entities.User;
import org.bson.types.ObjectId;
import tn.esprit.piproject.Repositories.RoleRepository;
import tn.esprit.piproject.request.UserC;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Repository
    public interface UserRep extends MongoRepository<User, ObjectId> {

    @Autowired
    RoleRepository roleRepository = null;
    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        void deleteById(ObjectId id);

        Optional<User> findById(ObjectId id);
    // Add method to update a user by ID

    default void updateById(ObjectId id, UserC updatedUser) {
        if (findById(id).isPresent()){
            Set<String> strRoles = updatedUser.getRoles();
            Set<Role> roles = new HashSet<>();
            
            // Associer chaque rôle spécifié à l'utilisateur
            strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        case "sup":
                            Role modRole = roleRepository.findByName(ERole.SUPERVISOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;
                        case "tutor":
                            Role tutRole = roleRepository.findByName(ERole.TUTOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(tutRole);
                            break;
                        case "student":
                            Role studentRole = roleRepository.findByName(ERole.STUDENT)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(studentRole);
                            break;
                    }
                });
            
            User user1 = new User(updatedUser.getName(), updatedUser.getLastName(),updatedUser.getAddress(),updatedUser.getEmail(),
                   roles);
            user1.setPassword(findById(id).get().getPassword());
            deleteById(id);
            save(user1);

        }
    }
    }