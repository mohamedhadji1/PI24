package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.Company;
import tn.esprit.piproject.Entities.Complaint;
import tn.esprit.piproject.Entities.Documents;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    @Autowired
    private IProjectService iProjectService;
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaint() {
        List<Complaint> complaints = iProjectService.getAllComplaint();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
    // Get documents by id
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable int id) {
        Optional<Complaint> complaint = iProjectService.getComplaintById(id);
        if (complaint.isPresent()) {
            return new ResponseEntity<>(complaint.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create documents
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        try {
            Complaint newComplaint = iProjectService.createComplaint(complaint);
            return new ResponseEntity<>(newComplaint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update documents
    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable int id, @RequestBody Complaint complaint) {
        Optional<Complaint> oldComplaint = iProjectService.getComplaintById(id);
        if (oldComplaint.isPresent()) {
            complaint.setIdComp(id);
            Complaint updatedComplaint = iProjectService.updateComplaint(complaint);
            return new ResponseEntity<>(updatedComplaint, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    // Delete documents

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable int id) {
        iProjectService.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
