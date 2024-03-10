package tn.esprit.piproject.Services;

import tn.esprit.piproject.Entities.*;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    /*******USER*********/
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    /*******InternShips********/
    List<Internship> getAllinternships();
    Optional<Internship> getinternshipById(int id);
    Internship createInternship(Internship internship);
    Internship updateinternship(Internship internship);
    void deleteinternship(int id);
    /*******Documents*********/
    List<Documents> getAlldocuments();
    Optional<Documents> getdocumentsById(int id);
    Documents createdocuments(Documents documents);
    Documents updatedocuments(Documents documents);
    void deletedocuments(int id);
    /******Tasks*****/
    List<Task> getAllTasks();
    Optional<Task> getTaskById(int id);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(int id);
    String getAttachmentFilename(int id);
    Resource downloadTaskAttachment(int taskId);
    /**************Offer******************/
    /*************Company******************/
    List<Company> getAllcompany();

    Optional<Company> getCompanyById(int idComp);

    Company updatecompany(Company company);

    void deletecompany(int idComp);

    /************Monitoring**************/
    List<MonitoringNote> getAllMonitoringNotes();
    Optional<MonitoringNote> getMonitoringNoteById(String id);
    MonitoringNote createMonitoringNote(MonitoringNote monitoringNote);
    MonitoringNote updateMonitoringNote(MonitoringNote monitoringNote);
    void deleteMonitoringNoteById(String id);

    /************Notification**************/
    List<Notification> getAllNotifications();
    Optional<Notification> getNotificationById(String id);
    Notification createNotification(Notification notification);
    Notification updateNotification(Notification notification);
    void deleteNotificationById(String id);

    /*****ChatMessage********/
    ChatMessage saveMessage(ChatMessage message);
    List<ChatMessage> getAllMessages();
    List<ChatMessage> getMessagesBetweenSupervisorAndStudent(int supervisorId, int studentId);
}

