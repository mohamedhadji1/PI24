package tn.esprit.piproject.Services;

import org.bson.types.ObjectId;
import tn.esprit.piproject.Entities.*;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    List<Internship> getAllinternships();
    Optional<Internship> getinternshipById(int id);
    Internship createInternship(Internship internship);
    Internship updateinternship(Internship internship);
    void deleteinternship(int id);
    List<Documents> getAlldocuments();
    Optional<Documents> getdocumentsById(int id);
    Documents createdocuments(Documents documents);
    Documents updatedocuments(Documents documents);
    void deletedocuments(int id);
/////////////////////////////////////DEFENCE////////////////////////////////
    List<Defense> getAllDefence();
    Optional<Defense> getDefenceById(int id);
    Defense createDefence(Defense defence);
    Defense updateDefence(Defense defence);
    void deleteDefence(int id);
    List<Evaluation> getAllEvalution();
    Optional<Evaluation> getEvalutioneById(int id);
    Evaluation createEvalution(Evaluation evaluation);
    Evaluation updateEvalution(Evaluation evaluation);
    void deleteEvalution(int id);
     List<Defense> getAllDefenses() ;
    List<User> getAllUserss();

}
