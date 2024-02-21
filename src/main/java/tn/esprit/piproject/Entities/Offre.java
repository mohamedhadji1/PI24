package tn.esprit.piproject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "offres")
public class Offre {

    private int id;
    private Date dateStart;
    private Date dateEnd;
    private TypeInternship typeInternship;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public TypeInternship getTypeInternship() {
        return typeInternship;
    }

    public void setTypeInternship(TypeInternship typeInternship) {
        this.typeInternship = typeInternship;
    }
}
