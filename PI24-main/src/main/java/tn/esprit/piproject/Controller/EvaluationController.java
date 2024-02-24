package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.Defence;
import tn.esprit.piproject.Entities.Evaluation;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/Evaluation")
public class EvaluationController {
    @Autowired
    private IProjectService iProjectService;

    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluation() {
        List<Evaluation> evaluations = iProjectService.getAllEvalution();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
    // Get Evaluation by id
    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable ObjectId id) {
        Optional<Evaluation> evaluations = iProjectService.getEvalutioneById(id);
        if (evaluations.isPresent()) {
            return new ResponseEntity<>(evaluations.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create Evaluation
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        try {
            Evaluation newEvaluation = iProjectService.createEvalution(evaluation) ;
            return new ResponseEntity<>(newEvaluation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}