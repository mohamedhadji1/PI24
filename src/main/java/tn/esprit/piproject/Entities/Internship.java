package tn.esprit.piproject.Entities;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@Document(collection = "internships")

public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idCompany;
    private String duration;
    private String subject;
    private String description;
    private String degreeStageO;
    private TypeInternship Type;
}
