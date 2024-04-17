package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.piproject.Entities.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends MongoRepository<Complaint, Integer> {
    public List<Complaint> getComplaintByEmail(String email);

    List<Complaint> findByIdCompAndUserId(int idComp, int userId);


    List<Complaint> findByUserId(int userId);
}