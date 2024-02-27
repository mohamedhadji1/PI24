package tn.esprit.piproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Services.IProjectImp;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private IProjectService iProjectService;

    // Récupérer toutes les offres
    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = iProjectService.getAllOffer();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    // Récupérer une offre par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable int id) {
        Optional<Offer> offer = iProjectService.getofferById(id);
        return offer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Créer une nouvelle offre
    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer newOffer = iProjectService.createoffer(offer);
        return new ResponseEntity<>(newOffer, HttpStatus.CREATED);
    }

    // Mettre à jour une offre existante
    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable int id, @RequestBody Offer offer) {
        Offer offer_from_db=iProjectService.getofferById(id).orElseGet(null);
        if(offer_from_db == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        offer_from_db.setTypeInternship(offer.getTypeInternship());
        Offer updatedOffer = iProjectService.updateoffer(offer_from_db);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    // Supprimer une offre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable int id) {
        iProjectService.deleteoffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}