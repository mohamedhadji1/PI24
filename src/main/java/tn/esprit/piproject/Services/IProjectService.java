package tn.esprit.piproject.Services;

import tn.esprit.piproject.Entities.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

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
    List<Task> getAllTasks();
    Optional<Task> getTaskById(int id);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(int id);
    Monitoring createTaskMonitoring (Monitoring monitoring);
    Resource downloadTaskAttachment(int taskId);
    String getAttachmentFilename(int id);
    /*****************************************/
    List<Offer> getAllOffer();

    Optional<Offer> getofferById(int id);
    List<Offer> getoffersByCompany(int id);

    Offer createoffer(Offer offer);

    Offer updateoffer(Offer offer);

    void deleteoffer(int id);
    /***************************************/
    List<Company> getAllcompany();

    Optional<Company> getCompanyById(int idComp);

    Company createcompany(Company company);

    Company updatecompany(Company company);

    void deletecompany(int idComp);
    /***************************************/
}

