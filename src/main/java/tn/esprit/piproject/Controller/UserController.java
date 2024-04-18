package tn.esprit.piproject.Controller;

import com.mongodb.MongoException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.ERole;
import tn.esprit.piproject.Entities.Internship;
import tn.esprit.piproject.Entities.Role;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Repositories.RoleRepository;
import tn.esprit.piproject.Repositories.UserRepository;
import tn.esprit.piproject.Repositories.response.UserRep;
import tn.esprit.piproject.Services.IProjectService;
import tn.esprit.piproject.request.UserC;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserRep userRep;
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private AutoIncrementUtil autoIncrementUtil;
    @Autowired
    PasswordEncoder encoder;
    //@Autowired
   // private UserRepository userRep;
    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = iProjectService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            Optional<User> user = userRep.findById(new ObjectId(id));
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get user: " + e.getMessage());
        }
    }
    /*
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = iProjectService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    // Create user
    @PostMapping("/users")
    public User addChambre(@RequestBody UserC user) {
            Set<String> strRoles = user.getRoles();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                // Si aucun rôle spécifié, attribuer le rôle par défaut (STUDENT)
                Role userRole = roleRepository.findByName(ERole.STUDENT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
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
                        default:
                            Role defaultRole = roleRepository.findByName(ERole.STUDENT)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(defaultRole);
                    }
                });
            }
            User user1 = new User(user.getName(), user.getLastName(),user.getAddress(),user.getEmail(),
                roles);
        return userRepository.save(user1);
    }

    // Update user
    @PutMapping("/users/{id}")
    public ResponseEntity<UserC> updateUserById(@PathVariable String id, @RequestBody UserC updatedUser) {
        if (userRep.findById(new ObjectId(id)).isPresent()){

            Set<String> strRoles = updatedUser.getRoles();
            Set<Role> roles = new HashSet<>();
            // Associer chaque rôle spécifié à l'utilisateur
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "SUPERVISOR":
                        Role modRole = roleRepository.findByName(ERole.SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "TUTOR":
                        Role tutRole = roleRepository.findByName(ERole.TUTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(tutRole);
                        break;
                    case "STUDENT":
                        Role studentRole = roleRepository.findByName(ERole.STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(studentRole);
                        break;
                }
            });
            User user1 = new User(updatedUser.getName(), updatedUser.getLastName(),updatedUser.getAddress(),updatedUser.getEmail(),
                    roles);
            user1.setPassword(userRep.findById(new ObjectId(id)).get().getPassword());
            userRep.deleteById(new ObjectId(id));
            userRep.save(user1);
            return ResponseEntity.ok().body(updatedUser); // Return the updated user
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return an error response
        }
    }
    // Delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            userRep.deleteById(new ObjectId(id));
            return ResponseEntity.noContent().build();
        } catch (MongoException e) {
            // Log the exception for debugging purposes
            System.out.println("Failed to delete user with ID: {} "+ id +" "+ e);
            return ResponseEntity.notFound().build();
        }
    }




}
