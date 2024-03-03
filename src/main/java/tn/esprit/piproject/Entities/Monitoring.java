
package tn.esprit.piproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Monitoring")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monitoring {

    @Id
    private String id;
    private String taskId;
    private String supervisorId;
    private String studentId;
    private List<Note> notes;
    private int percentageCompletion;
    private String status;
}

