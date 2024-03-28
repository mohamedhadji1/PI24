package tn.esprit.piproject.Entities;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

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
        TODO,
        IN_PROGRESS,
        COMPLETE
    }

    public MonitoringNote(String note, double percentage, MonitoringStatus status, User updater) {
        this.note = note;
        this.percentageComplete = percentage;
        this.status = status;
        this.updater = updater;
    }
}
