package tn.esprit.piproject.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


public enum ERole {
    ADMIN,
    STUDENT,
    SUPERVISOR,
    TUTOR
}
