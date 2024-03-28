package tn.esprit.piproject.Entities;

import com.mongodb.lang.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;
@Document(collection = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String taskDescription;
    private String progress;
    private String duration;
    @Nullable
    private String attachmentFileName;
    @Nullable
    private byte[] attachmentData;
    @DBRef
    private User supervisor;
    @DBRef
    private User student;
}

