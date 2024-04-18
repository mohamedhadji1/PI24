package tn.esprit.piproject.Controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tn.esprit.piproject.Entities.ERole;
import tn.esprit.piproject.Entities.Role;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Repositories.RoleRepository;
import tn.esprit.piproject.Repositories.UserRepository;
import tn.esprit.piproject.Repositories.response.MessageResponse;
import tn.esprit.piproject.Repositories.response.UserInfoResponse;
import tn.esprit.piproject.Security.jwt.JwtUtils;
import tn.esprit.piproject.Security.services.UserDetailsImpl;
import tn.esprit.piproject.Services.service_email;
import tn.esprit.piproject.request.LoginRequest;
import tn.esprit.piproject.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private String savedToken;

    @Autowired
    private service_email emailService;
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        super();
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            // Authentification de l'utilisateur
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Définition de l'authentification dans le contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Récupération des détails de l'utilisateur
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Génération du token JWT
            String jwtToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());

            // Génération du cookie JWT et ajout à la réponse
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(jwtToken);

            // Récupération des rôles de l'utilisateur
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            System.out.println("token : "+jwtToken);
            this.savedToken=jwtToken;
            System.out.println("email : "+userDetails.getEmail());

                emailService.sendEmail(userDetails.getEmail(), "Veirfication du Connexion", "Votre token de connexion : "+jwtToken);


            // Retour de la réponse avec le cookie JWT et les informations de l'utilisateur
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header("Authorization", "Bearer " + jwtToken) // Add token to response header
                    .body(new UserInfoResponse(userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            roles));
        } catch (Exception e) {
            // Gérer les exceptions et renvoyer une réponse d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error while authenticating user: " + e.getMessage()));
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            // Vérifier si le nom d'utilisateur est déjà pris
            if (userRepository.existsByname(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
            }

            // Vérifier si l'email est déjà utilisé
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            }

            // Créer un nouvel utilisateur avec les données de la demande
            User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            // Récupérer les rôles spécifiés dans la demande et les associer à l'utilisateur
            /*Set<String> strRoles = signUpRequest.getRoles();
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

            // Associer les rôles à l'utilisateur et enregistrer l'utilisateur dans la base de données
            user.setRoles(roles);*/
            userRepository.save(user);

            // Renvoyer une réponse de réussite
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            // Gérer les exceptions et renvoyer une réponse d'erreur en cas de problème
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error while registering user: " + e.getMessage()));
        }
    }

    @GetMapping("/verif/{token}")
    public ResponseEntity<User> getUserById(@PathVariable String token) {
        System.out.println("saved token : "+this.savedToken );
        if (this.savedToken.equals(token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
