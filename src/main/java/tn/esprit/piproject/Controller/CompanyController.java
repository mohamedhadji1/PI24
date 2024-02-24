package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.Company;
import tn.esprit.piproject.Entities.Documents;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private IProjectService iProjectService;
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        List<Company> companies = iProjectService.getAllcompany();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
    // Get documents by id
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Optional<Company> company = iProjectService.getcompanyById(id);
        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Create documents
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        try {
            Company newCompany = iProjectService.createcompany(company);
            return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update documents
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company company) {
        Optional<Company> oldCompany = iProjectService.getcompanyById(id);
        if (oldCompany.isPresent()) {
            company.setIdComp(id);
            Company updatedCompany = iProjectService.updatecompany(company);
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    // Delete documents

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        iProjectService.deletecompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
