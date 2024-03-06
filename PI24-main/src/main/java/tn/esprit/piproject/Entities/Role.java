package tn.esprit.piproject.Entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    private ERole name;




}
