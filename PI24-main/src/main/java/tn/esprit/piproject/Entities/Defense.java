package tn.esprit.piproject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "defences")
public class Defense {
    @MongoId
    @Id
    private int  idDef;

    @JsonProperty("dateDefense")
    private Date dateDefense;
    @JsonProperty("timeDefense")
    private String timeDefense;
    @JsonProperty("numeroDeBloc")
    private String numeroDeBloc;
    @JsonProperty("numeroDeClasse")
    private int numeroDeClasse;
    @JsonProperty("nomDeJuret")
    private String TUTOR;
    //private String STUDENT  ;
    @JsonProperty("UserStudent")
    @DBRef
    private User UserStudent ;

    @JsonProperty("nomDeEncadrent")
    private String nomDeEncadrent;

    @JsonProperty("remarque")
    private String remarque;

   public void generateAttributes() {
        // Génération automatique de numeroDeBloc
        String[] blocs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M"};
        int randomBlocIndex = (int) (Math.random() * blocs.length);
        this.numeroDeBloc = blocs[randomBlocIndex];

        // Génération automatique de numeroDeClasse
        this.numeroDeClasse = (int) (Math.random() * 15) + 1; // Valeurs de 01 à 15
    }
    public void modifyAttributes(String newNumeroDeBloc, int newNumeroDeClasse) {
        // Vérifier si la classe existe déjà
        // Si elle existe déjà, afficher un message ou lancer une exception
        // Sinon, modifier les attributs
        this.numeroDeBloc = newNumeroDeBloc;
        this.numeroDeClasse = newNumeroDeClasse;
    }
}
