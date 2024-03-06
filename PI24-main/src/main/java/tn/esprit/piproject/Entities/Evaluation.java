package tn.esprit.piproject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "evaluations")
public class Evaluation {
@MongoId
    private int id;
    @DBRef
      private Defense defense ;
    @DBRef
    private HistoriqueDefense historiqueDefense ;
    private  String  tutor;
    private String  student  ;
    private  double note  ;
    private String description;





}
