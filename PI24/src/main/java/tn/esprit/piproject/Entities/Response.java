package tn.esprit.piproject.Entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRep;
    private String message;
    private LocalDateTime responseDate;
    private int complaintId;
    private int userId ;

    private SatisfactionLevel note;

    public void setComplaint(Complaint complaint) {
        this.complaintId = complaint.getIdComp();
    }

    public Response(String msg, int cmId){
        this.complaintId=cmId;
        this.message=msg;
        this.userId=2;
        this.responseDate=LocalDateTime.now();
    }

}
