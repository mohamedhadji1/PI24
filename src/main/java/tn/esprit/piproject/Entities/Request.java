package tn.esprit.piproject.Entities;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Request {
    @Id
    private int id ;
    @DBRef
    private Offer offer ;
    @DBRef
    private Company company;
    private  boolean submit ;
    @DBRef
    private  User Student ;
}
