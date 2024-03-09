package tn.esprit.piproject.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    private int id;
    private String message;
    private boolean read;
    @DBRef
    private User sender;
    @DBRef
    private User recipient;
}