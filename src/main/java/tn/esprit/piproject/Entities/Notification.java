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
public class Notification {

    @Id
    private int id;
    private String description;
    private String sender;
    private User reciever;

    public Notification(User supervisor, String taskDescription) {
    }
}