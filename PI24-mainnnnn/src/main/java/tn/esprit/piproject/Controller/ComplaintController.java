package tn.esprit.piproject.Controller;


import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.piproject.Entities.Complaint;
import tn.esprit.piproject.Entities.ComplaintStatus;
import tn.esprit.piproject.Entities.Response;
import tn.esprit.piproject.Entities.SatisfactionLevel;
import tn.esprit.piproject.Services.IProjectService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/complaint")
public class ComplaintController {


    private final IProjectService iProjectService;

    public ComplaintController(final IProjectService iProjectService){
        this.iProjectService=iProjectService;

    }

    @GetMapping("getAllByUserId/{userId}")
    public ResponseEntity<List<Complaint>> getAllComplaintByUserId(@PathVariable int userId) {
        List<Complaint> complaints = iProjectService.getAllComplaintByUserId(userId);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaint() {
        List<Complaint> complaints = iProjectService.getAllComplaint();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
    // Get complaint by id
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable int id) {
        Optional<Complaint> complaint = iProjectService.getComplaintById(id);
        return complaint.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create complaint
    @PostMapping
    public ResponseEntity<Complaint> createComplaint  (@RequestBody Complaint complaint) {
        try {
            complaint.setStatus(ComplaintStatus.IN_PROGRESS);
            Complaint newComplaint = iProjectService.createComplaint(complaint);
            return new ResponseEntity<>(newComplaint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update complaint
     /*   @PutMapping("/{id}")
        public ResponseEntity<Complaint> updateComplaint(@PathVariable int id, @RequestBody Complaint updatedComplaint) {
            Optional<Complaint> optionalComplaint = iProjectService.getComplaintById(id);
            if (optionalComplaint.isPresent()) {
                Complaint complaint = optionalComplaint.get();
                complaint.setDescription(updatedComplaint.getDescription());
                complaint.setTypeRec(updatedComplaint.getTypeRec());
                complaint.setDateComplaint(updatedComplaint.getDateComplaint());
                complaint.setName(updatedComplaint.getName());
                complaint.setLastname(updatedComplaint.getLastname());
                complaint.setEmail(updatedComplaint.getEmail());
                complaint.setStatus(updatedComplaint.getStatus());
                Complaint updated = iProjectService.updateComplaint(complaint);
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


*/
    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable int id, @RequestBody Complaint updatedComplaint) {
        Optional<Complaint> optionalComplaint = iProjectService.getComplaintById(id);
        if (optionalComplaint.isPresent()) {
            Complaint complaint = optionalComplaint.get();
            complaint.setDescription(updatedComplaint.getDescription());
            complaint.setTypeRec(updatedComplaint.getTypeRec());
            complaint.setDateComplaint(updatedComplaint.getDateComplaint());
            complaint.setName(updatedComplaint.getName());
            complaint.setLastname(updatedComplaint.getLastname());
            complaint.setEmail(updatedComplaint.getEmail());
            complaint.setStatus(updatedComplaint.getStatus());
            complaint.setMessage(updatedComplaint.getMessage());

            Complaint updated = iProjectService.updateComplaint(complaint);

            // Créer une réponse uniquement si le statut est "TREATED"
            if (complaint.getStatus() == ComplaintStatus.TREATED) {
                Response response = new Response(updatedComplaint.getMessage(), complaint.getIdComp());
                iProjectService.createResponse(response);
            } else {
                System.out.println("pas de creation de reponse ");
            }

            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/complaints/{complaintId}/user/{userId}")
    public ResponseEntity<List<Complaint>> getComplaintsByComplaintIdAndUserId(
            @PathVariable int idComp, @PathVariable int userId) {
        List<Complaint> complaints = iProjectService.getComplaintsByComplaintIdAndUserId(idComp, userId);
        if (complaints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }



    @GetMapping("/getComplaintByUserId")
    public ResponseEntity<List<Complaint>> getComplaintByUserId() {
        List<Complaint> complaints  = iProjectService.getComplaintsByUserId(2);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }



    // Delete complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable int id) {
        iProjectService.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
