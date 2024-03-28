package tn.esprit.piproject.Entities;
import com.mongodb.lang.Nullable;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private Date timestamp;
    private NotificationStatus status;
    @DBRef
    @Nullable
    private User sender;
    @DBRef
    @Nullable
    private User recipient;
}

