package tn.esprit.piproject.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.piproject.Entities.*;
import tn.esprit.piproject.Repositories.CompanyRepository;
import tn.esprit.piproject.Repositories.OfferRepository;
import tn.esprit.piproject.Repositories.UserRepository;
import tn.esprit.piproject.Services.IProjectService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/request")
public class RequestController {
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OfferRepository offreRepository;
    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping
    public ResponseEntity<List<Request>> getallrequests() {
        List<Request> request = iProjectService.getallrequests();
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Request> createrequest( @RequestParam("req") String requestJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Request request = objectMapper.readValue(requestJson, Request.class);
            Offer offer = offreRepository.findById(request.getOffer().getId()).orElse(null);
            User student = userRepository.findById(request.getStudent().getId()).orElse(null);
            User supervisor = userRepository.findById(request.getStudent().getId()).orElse(null);
            if (offer == null || student == null || supervisor == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            request.setOffer(offer);
            request.setStudent(student);
            request.setStudent(supervisor);
            request.setSubmit(true);
            Request createrequest = iProjectService.createrequest(request);
            return new ResponseEntity<>(createrequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
