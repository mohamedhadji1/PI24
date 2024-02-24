package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.Defence;
import tn.esprit.piproject.Entities.Documents;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/Defence")
@CrossOrigin(origins = "http://localhost:4200")
public class DefenceController {
    @Autowired
    private IProjectService iProjectService;
    @GetMapping
    public ResponseEntity<List<Defence>> getAllDefence() {
        List<Defence> defences = iProjectService.getAllDefence();
        return new ResponseEntity<>(defences, HttpStatus.OK);
    }
    // Get defence by id
    @GetMapping("/{id}")
    public ResponseEntity<Defence> getDefenceById(@PathVariable ObjectId id) {
        Optional<Defence> defence = iProjectService.getDefenceById(id);
        if (defence.isPresent()) {
            return new ResponseEntity<>(defence.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create documents
    @PostMapping
    public ResponseEntity<Defence> createDefence(@RequestBody Defence defence) {
        try {
            Defence newDefence = iProjectService.createDefence(defence) ;
            return new ResponseEntity<>(newDefence, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update defence
    @PutMapping("/{id}")
    public ResponseEntity<Defence> updateDefence(@PathVariable ObjectId id, @RequestBody Defence defences) {
        Optional<Defence> oldDefence = iProjectService.getDefenceById(id) ;
        if (oldDefence.isPresent()) {
            defences.setIdDef(id);
            Defence updateDefences = iProjectService.updateDefence(defences) ;
            return new ResponseEntity<>(updateDefences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete defence
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefence(@PathVariable ObjectId id) {
        iProjectService.deleteDefence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
