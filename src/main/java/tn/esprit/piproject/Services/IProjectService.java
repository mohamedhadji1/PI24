package tn.esprit.piproject.Services;

import tn.esprit.piproject.Entities.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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
    /************MonitoringNotes**************/

    List<MonitoringNote> getAllMonitoringNotes();

    Optional<MonitoringNote> getMonitoringNoteById(int id);

    MonitoringNote createMonitoringNote(MonitoringNote note);

    MonitoringNote updateMonitoringNote(MonitoringNote note);

    void deleteMonitoringNoteById(int id);
    /************Notifications**************/

    void sendAssignmentNotification(Task task);
    void sendTaskCompletionNotification(Task task);
    List<Notification> getAllNotifications();
    int getUnreadNotificationCount();
    Optional<Notification> getNotificationById(int id);

    Notification createNotification(Notification notification);

    Notification updateNotification(Notification notification);

    void deleteNotificationById(int id);
    /********ChatMessages********/

    List<ChatMessage> getAllChatMessages();

    Optional<ChatMessage> getChatMessageById(int id);

    ChatMessage createChatMessage(ChatMessage message);

    ChatMessage updateChatMessage(ChatMessage message);

    void deleteChatMessageById(int id);

    /************Monitoring**************/

    List<Monitoring> getAllMonitorings();

    Optional<Monitoring> getMonitoringById(String id);

    Monitoring createMonitoring(Monitoring monitoring);

    Monitoring updateMonitoring(Monitoring monitoring);

    void deleteMonitoringById(String id);
    /*********Request**********/
     List<Request> getallrequests();
     Request createrequest ( Request request);
}

