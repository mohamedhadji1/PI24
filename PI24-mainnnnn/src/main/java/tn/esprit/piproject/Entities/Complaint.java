package tn.esprit.piproject.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "complaints")
public class Complaint {


    @Id private int idComp;
    private String description;
    private TypeRec typeRec;
    private LocalDateTime dateComplaint;
    private String name;
    private String lastname;
    private String email;
    private String message;
    private int userId=2 ;




    private ComplaintStatus status = getStatus();

    private SatisfactionLevel note;

}
