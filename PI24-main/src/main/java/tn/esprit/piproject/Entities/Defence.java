package tn.esprit.piproject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "defences")
public class Defence {
    @MongoId
    private ObjectId idDef;

    @JsonProperty("dateDefence")
    private Date dateDefence;

    @JsonProperty("numeroDeClasse")
    private int numeroDeClasse;

    @JsonProperty("nomDeJuret")
    private String nomDeJuret;

    @JsonProperty("nomDeEncafrent")
    private String nomDeEncafrent;

    @JsonProperty("remarque")
    private String remarque;
}
