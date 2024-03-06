package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.Defense;
import tn.esprit.piproject.Entities.Evaluation;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/Evaluation")
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationController {
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private AutoIncrementUtil autoIncrementUtil;

    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluation() {
        List<Evaluation> evaluations = iProjectService.getAllEvalution();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
    // Get Evaluation by id
    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable int id) {
        Optional<Evaluation> evaluations = iProjectService.getEvalutioneById(id);
        if (evaluations.isPresent()) {
            return new ResponseEntity<>(evaluations.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create Evaluation
    /*@PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@PathVariable Integer defenseId ,@RequestBody Evaluation evaluation) {
        int id = autoIncrementUtil.getNextSequence("votre_sequence");

        try {
            evaluation.setId(id);
            Evaluation newEvaluation = iProjectService.createEvalution(evaluation) ;
            return new ResponseEntity<>(newEvaluation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    /*@PostMapping("/{defenseId}")
    public ResponseEntity<Evaluation> createEvaluation(@PathVariable Integer defenseId, @RequestBody Evaluation evaluation) {
        int id = autoIncrementUtil.getNextSequence("votre_sequence");

        try {
            evaluation.setId(id);
            Evaluation newEvaluation = iProjectService.createEvalution(evaluation);
            return new ResponseEntity<>(newEvaluation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @PostMapping("/{defenseId}")
    public ResponseEntity<Void> createEvaluation(@PathVariable Integer defenseId, @RequestBody Evaluation evaluation) {
        int id = autoIncrementUtil.getNextSequence("votre_sequence");

        try {
            evaluation.setId(id);
            iProjectService.createEvaluationWithHistory(defenseId, evaluation);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable int id, @RequestBody Evaluation evaluation) {
        Optional<Evaluation> oldEvaluation = iProjectService.getEvalutioneById(id) ;
        if (oldEvaluation.isPresent()) {
            evaluation.setId(id);
            Evaluation updateEvaluation= iProjectService.updateEvalution(evaluation) ;
            return new ResponseEntity<>(updateEvaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Delete defence
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable int id) {
        iProjectService.deleteEvalution(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}