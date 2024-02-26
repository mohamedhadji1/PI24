package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.Company;
import tn.esprit.piproject.Entities.Task;
import tn.esprit.piproject.Repositories.CompanyRepository;
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
    @Autowired
    private CompanyRepository companyRepository;
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        List<Company> companies = companyRepository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
    // Get documents by id
    @GetMapping("/{idComp}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int idComp) {
        Optional<Company> company = companyRepository.findById(idComp);
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
    @PutMapping("/{idComp}")
    public ResponseEntity<Company> updateCompany(@PathVariable int idComp, @RequestBody Company company) {
        Optional<Company> oldCompany = companyRepository.findById(idComp);
        if (oldCompany.isPresent()) {
            company.setId(idComp);
            Company updatedCompany = iProjectService.updatecompany(company);
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    // Delete documents

    @DeleteMapping("/{IdComp}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("IdComp") int IdComp) {
        Company existingTask = companyRepository.findById(IdComp).orElse(null);
        if (existingTask != null) {
            companyRepository.deleteById(IdComp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}