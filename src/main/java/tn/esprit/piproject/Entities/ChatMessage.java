package tn.esprit.piproject.Entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "chat_messages")
public class ChatMessage {

    @Id
    private int id;

    @DBRef
    private Task task;

    @DBRef
    private User sender;

    @DBRef
    private User recipient;

    private String message;
    private Date timestamp;

}