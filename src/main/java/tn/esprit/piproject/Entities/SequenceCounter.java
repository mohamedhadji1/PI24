package tn.esprit.piproject.Entities;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	
}