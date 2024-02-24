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
import tn.esprit.piproject.Entities.Response;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/response")
public class ResponseController {
    @Autowired
    private IProjectService iProjectService;
    @GetMapping
    public ResponseEntity<List<Response>> getAllResponse() {
        List<Response> response = iProjectService.getAllResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Get documents by id
    @GetMapping("/{id}")
    public ResponseEntity<Response> getComplaintById(@PathVariable int id) {
        Optional<Response> response = iProjectService.getResponseById(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create documents
    @PostMapping
    public ResponseEntity<Response> createResponse(@RequestBody Response response) {
        try {
            Response newResponse = iProjectService.createResponse(response);
            return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update documents
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable int id, @RequestBody Response response) {
        Optional<Response> oldResponse = iProjectService.getResponseById(id);
        if (oldResponse.isPresent()) {
            response.setIdRep(id);
            Response updatedResponse = iProjectService.updateResponse(response);
            return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    // Delete documents

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable int id) {
        iProjectService.deleteResponse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
