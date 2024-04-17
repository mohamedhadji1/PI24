package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Config.AutoIncrementUtil;
import tn.esprit.piproject.Entities.Defence;
import tn.esprit.piproject.Entities.HistoriqueDefense;
import tn.esprit.piproject.Repositories.DefenceRepository;
import tn.esprit.piproject.Repositories.HistoriqueDefenseRepository;
import tn.esprit.piproject.Services.IProjectService;

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
    private DefenceRepository defenseRepository;
    @Autowired
    private HistoriqueDefenseRepository historiqueDefenseRepository;



    /*  @GetMapping("/search")
      public List<HistoriqueDefense> searchHistoriques(@RequestParam String query) {
          return iProjectService.searchHistoriques(query);
      }
    @GetMapping("/search")
    public ResponseEntity<List<String> >searchHistoriques(@RequestParam String query) {
        if (query == null || query.isEmpty() || query.length() < 3) {
        //    return ResponseEntity.badRequest().body("Query must have at least 3 characters.");
        }

        List<HistoriqueDefense> historiques = iProjectService.searchHistoriques(query);
       // qreturn ResponseEntity.ok().body(historiques.toString());
    }*/
    @GetMapping("/search")
    public ResponseEntity<List<HistoriqueDefense>> searchHistoriques(@RequestParam String query) {
        System.out.println("Searching for historiques with query: " + query); // Debug log
        List<HistoriqueDefense> historiques = iProjectService.searchHistoriques(query);
        System.out.println("Searching for historiques with query: " + historiques); // Debug log

        return ResponseEntity.ok(historiques);
    }



    @GetMapping
    public ResponseEntity<List<Defence>> getAllDefence() {
        List<Defence> defences = iProjectService.getAllDefence();
        return new ResponseEntity<>(defences, HttpStatus.OK);
    }
    // Get defence by id
    @GetMapping("/{id}")
    public ResponseEntity<Defence> getDefenceById(@PathVariable int id) {
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
        int id = autoIncrementUtil.getNextSequence("votre_sequence");
        try {
            defence.setIdDef(id);
            Defence newDefence = iProjectService.createDefence(defence) ;
            return new ResponseEntity<>(newDefence, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update defence
    @PutMapping("/{id}")
    public ResponseEntity<Defence> updateDefence(@PathVariable int id, @RequestBody Defence defences) {
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
    public ResponseEntity<Void> deleteDefence(@PathVariable int id) {
        iProjectService.deleteDefence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/defenses")
    public ResponseEntity<List<Defence>> getAllDefenses() {
        List<Defence> defenses = iProjectService.getAllDefenses();
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


