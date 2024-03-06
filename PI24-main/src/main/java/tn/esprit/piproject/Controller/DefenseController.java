package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.Defense;
import tn.esprit.piproject.Entities.HistoriqueDefense;
import tn.esprit.piproject.Repositories.DefenseRepository;
import tn.esprit.piproject.Repositories.HistoriqueDefenseRepository;
import tn.esprit.piproject.Services.IProjectService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RequestMapping("/api/Defence")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RestController


public class DefenseController {
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private AutoIncrementUtil autoIncrementUtil;
    @Autowired
    private DefenseRepository defenseRepository;
    @Autowired
    private HistoriqueDefenseRepository historiqueDefenseRepository;



  /*  @GetMapping("/search")
    public List<HistoriqueDefense> searchHistoriques(@RequestParam String query) {
        return iProjectService.searchHistoriques(query);
    }*/
  @GetMapping("/search")
  public ResponseEntity<List<HistoriqueDefense>> searchHistoriques(@RequestParam String query) {
      List<HistoriqueDefense> historiques = iProjectService.searchHistoriques(query);
      return ResponseEntity.ok().body(historiques);
  }
    @GetMapping
    public ResponseEntity<List<Defense>> getAllDefence() {
        List<Defense> defences = iProjectService.getAllDefence();
        return new ResponseEntity<>(defences, HttpStatus.OK);
    }
    // Get defence by id
    @GetMapping("/{id}")
    public ResponseEntity<Defense> getDefenceById(@PathVariable int id) {
        Optional<Defense> defence = iProjectService.getDefenceById(id);
        if (defence.isPresent()) {
            return new ResponseEntity<>(defence.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create documents
    @PostMapping
    public ResponseEntity<Defense> createDefence(@RequestBody Defense defence) {
        int id = autoIncrementUtil.getNextSequence("votre_sequence");
        try {
            defence.setIdDef(id);
            Defense newDefence = iProjectService.createDefence(defence) ;
            return new ResponseEntity<>(newDefence, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update defence
    @PutMapping("/{id}")
    public ResponseEntity<Defense> updateDefence(@PathVariable int id, @RequestBody Defense defences) {
        Optional<Defense> oldDefence = iProjectService.getDefenceById(id) ;
        if (oldDefence.isPresent()) {
            defences.setIdDef(id);
            Defense updateDefences = iProjectService.updateDefence(defences) ;
            return new ResponseEntity<>(updateDefences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete defence
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefence(@PathVariable int id) {
        iProjectService.deleteDefence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/defenses")
    public ResponseEntity<List<Defense>> getAllDefenses() {
        List<Defense> defenses = iProjectService.getAllDefenses();
        return ResponseEntity.ok().body(defenses);
    }
    @GetMapping("/getAllHistoriqueDefense")
    public ResponseEntity<List<HistoriqueDefense>> getAllHistoriqueDefense() {
        List<HistoriqueDefense> historiqueDefense = iProjectService.getAllHistoriqueDefense();
        return ResponseEntity.ok().body(historiqueDefense);
    }

    //////afficher historiqueDefense par ID


    /*@GetMapping("/historique/{id}")
    public ResponseEntity<HistoriqueDefense> getHistoriqueDefenseById(@PathVariable int id) {
        Optional<HistoriqueDefense> historiqueDefense = iProjectService.gethistoriqueDefenceByIdById(id);
        if (historiqueDefense.isPresent()) {
            return new ResponseEntity<>(historiqueDefense.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

/*@GetMapping("/historique")
public ResponseEntity<Void> moveOldDefencesToHistory() {
    iProjectService.moveOldDefencesToHistory();
    return new ResponseEntity<>(HttpStatus.OK);
}
   // @GetMapping("/historique")
*/
@PostMapping("/transfer-to-history")
public ResponseEntity<String> transferDefensesToHistory() {
    try {
        iProjectService.transferOldDefensesToHistory();
        return new ResponseEntity<>("Transfert des défenses obsolètes vers l'historique réussi.", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Une erreur s'est produite lors du transfert des défenses vers l'historique.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    }


