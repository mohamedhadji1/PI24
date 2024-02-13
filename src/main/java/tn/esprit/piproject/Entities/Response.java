package tn.esprit.piproject.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
@Document(collection = "responses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRep;
    private String description;
}
