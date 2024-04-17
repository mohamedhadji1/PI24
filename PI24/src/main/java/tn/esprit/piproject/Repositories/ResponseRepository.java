package tn.esprit.piproject.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.piproject.Entities.Complaint;
import tn.esprit.piproject.Entities.Response;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends MongoRepository<Response, Integer> {
    public Response getResponsesByComplaint(Complaint complaint);
    public Response findResponseByComplaint_IdComp(int complaintId);
    Optional<Response> findByComplaint_IdComp(int idComp);

    Optional<Response> findByComplaintId(int complaintId);


    List<Response> findByUserId(int userId);

}