package tn.esprit.piproject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "HistoriqueDefense")
public class HistoriqueDefense {
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
    @DBRef
    private User nomDeJuret;
    //private String STUDENT  ;
    @JsonProperty("UserStudent")
    @DBRef
    private User UserStudent ;

    @JsonProperty("nomDeEncadrent")
    private String nomDeEncadrent;

    @JsonProperty("remarque")
    private String remarque;
    public HistoriqueDefense(Defense defense) {
        this.idDef = defense.getIdDef();
        this.dateDefense = defense.getDateDefense();
        this.timeDefense = defense.getTimeDefense();
        this.numeroDeBloc = defense.getNumeroDeBloc();
        this.numeroDeClasse = defense.getNumeroDeClasse();
        this.nomDeJuret = defense.getNomDeJuret();
        this.UserStudent = defense.getUserStudent();
        this.nomDeEncadrent = defense.getNomDeEncadrent();
        this.remarque = defense.getRemarque();
    }

}
