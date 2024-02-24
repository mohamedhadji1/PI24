package tn.esprit.piproject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "defences")
public class Defence {

    private int idDef;
    private static int idCounter = 0;

    public Defence() {

        this.idDef = ++idCounter;
    }

    public int getIdDef() {
        return idDef;
    }

    public void setIdDef(int idDef) {
        this.idDef = idDef;
    }
}
