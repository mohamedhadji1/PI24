package tn.esprit.piproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "chat_messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    private int id;
    private String text;
    private Date timestamp;
    @DBRef
    private User sender;
    @DBRef
    private User recipient;
}
