
package tn.esprit.piproject.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.BsonUndefined;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.piproject.Entities.Company;
import tn.esprit.piproject.Entities.Task;
import tn.esprit.piproject.Repositories.CompanyRepository;
import tn.esprit.piproject.Services.IProjectService;

import java.io.IOException;
import java.util.Base64;
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
    public ResponseEntity<Company> getCompanyById(@PathVariable int idComp, Model model) {
        Optional<Company> company = companyRepository.findById(idComp);
        if (company.isPresent()) {
            model.addAttribute("title", company.get().getAttachmentFileName());
            model.addAttribute("image", Base64.getEncoder().encodeToString(company.get().getAttachmentData().getData()));
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestParam("file") MultipartFile file, @RequestParam("company") String companyJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Company company = objectMapper.readValue(companyJson, Company.class);
            if (!file.isEmpty()) {
                company.setAttachmentFileName(file.getOriginalFilename());
                company.setAttachmentData(new Binary(BsonBinarySubType.BINARY, file.getBytes())
                );

            }
            Company createdCompany = iProjectService.createcompany(company);
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{idComp}")
    public ResponseEntity<Company> updateCompany(  @PathVariable int idComp, @RequestBody Company company) {
        Company oldCompany = companyRepository.findById(idComp).get();
        company.setId(oldCompany.getId());
        company.setAttachmentData(oldCompany.getAttachmentData());
        Company updatedCompany = iProjectService.updatecompany(company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);


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

