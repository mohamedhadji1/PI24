package tn.esprit.piproject.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "offer")
public class Offer {

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
