package tn.esprit.piproject.Entities;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private int id;

    private String name;
    private String lastName;
    private String email;
    private String motdepass;
    private Role role;
    private String address;
}
