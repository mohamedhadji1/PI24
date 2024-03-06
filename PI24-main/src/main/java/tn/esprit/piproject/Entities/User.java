package tn.esprit.piproject.Entities;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    private int id;

    /* @NotBlank*/
    private String name;

    /*@NotBlank*/
    private String lastName;

    /* @NotBlank
     @Email*/
    private String email;

    @Enumerated(EnumType.STRING)
    private ERole ERole;

    private Role roles;

    private String address;

    /*@NotBlank
    @Size(min = 6, max = 255)*/
    private String password;


}
