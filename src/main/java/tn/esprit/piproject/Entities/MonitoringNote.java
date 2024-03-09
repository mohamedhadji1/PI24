package tn.esprit.piproject.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "monitoring_notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonitoringNote {
    @Id
    private int id;
    private String note;
    private double percentageComplete;
    private MonitoringStatus status;
    @DBRef
    private User updater;
    private Date updatedAt;
    public enum MonitoringStatus {
        TODO, IN_PROGRESS, COMPLETE
    }
}