package tn.esprit.piproject.Entities;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Getter
@Setter
public class SequenceCounter {

    @Id
    private String id;

    private int seq;

    public SequenceCounter(String id, int seq) {
        this.id = id;
        this.seq = seq;
    }
}
