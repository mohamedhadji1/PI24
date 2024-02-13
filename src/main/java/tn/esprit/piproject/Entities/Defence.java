package tn.esprit.piproject.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "defences")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Defence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDef;
}
