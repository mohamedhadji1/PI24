package tn.esprit.piproject.Services;

import tn.esprit.piproject.Entities.*;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    /******Tasks*****/
    List<Task> getAllTasks();
    Optional<Task> getTaskById(int id);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(int id);
    String getAttachmentFilename(int id);
    Resource downloadTaskAttachment(int taskId);
    List<Task> searchTasksByDescription(String keyword);
    List<Task> searchTasksByProgress(String keyword);
    List<Task> searchTasksByDuration(String keyword);
    List<Task> searchTasksBySupervisorName(String keyword);
    List<Task> searchTasksByStudentName(String keyword);

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

    /**************Offer******************/
    List<Offer> getAllOffer();

    Optional<Offer> getofferById(int id);
    List<Offer> getoffersByCompany(int id);

    Offer createoffer(Offer offer);

    Offer updateoffer(Offer offer);

    void deleteoffer(int id);
    /*************Company******************/
    List<Company> getAllcompany();

    Optional<Company> getCompanyById(int idComp);

    Company createcompany(Company company);

    Company updatecompany(Company company);

    void deletecompany(int idComp);

    /************Monitoring**************/
    List<MonitoringNote> getAllMonitoringNotes();
    Optional<MonitoringNote> getMonitoringNoteById(int id);
    MonitoringNote createMonitoringNote(MonitoringNote monitoringNote);
    MonitoringNote updateMonitoringNote(MonitoringNote monitoringNote);
    void deleteMonitoringNoteById(int id);
    List<MonitoringNote> getMonitoringNotesByStatus(Status status);
    /************Notification**************/
    Notification saveNotification(Notification notification);
    /*****ChatMessage********/
    ChatMessage saveMessage(ChatMessage message);
    List<ChatMessage> getAllMessages();
    List<ChatMessage> getMessagesBetweenSupervisorAndStudent(int supervisorId, int studentId);
    /*****TurnIn********/
    TurnIn submitTurnIn(TurnIn turnIn);

    List<TurnIn> getAllTurnIns();

    TurnIn getTurnInById(int turnInId);

    List<TurnIn> getTurnInsByStudentId(int studentId);
    /*********Request**********/
    List<Request> getallrequests();
    Request createrequest ( Request request);
    /**************************Defnse**********************/

    void transferOldDefensesToHistory()   ;


    ///////////////////////////////HistoriqueDefense///////////////////////
    Optional<HistoriqueDefense> gethistoriqueDefenceByIdById(int id);
    //public Date getDateThreshold()  ;
    //public void moveOldDefencesToHistory()  ;

    /////////////////////////////////////DEFENCE////////////////////////////////
    List<Defence> getAllDefence();
    Optional<Defence> getDefenceById(int id);
    Defence createDefence(Defence defence);
    Defence updateDefence(Defence defence);
    void deleteDefence(int id);
    List<Evaluation> getAllEvalution();
    Optional<Evaluation> getEvalutioneById(int id);
    Evaluation createEvalution(Evaluation evaluation);
    Evaluation updateEvalution(Evaluation evaluation);
    void deleteEvalution(int id);
    List<Defence> getAllDefenses() ;
    List<User> getAllUserss();
    public void createEvaluationWithHistory(int defenseId, Evaluation evaluation) ;
    List<HistoriqueDefense> getAllHistoriqueDefense();
    public List<HistoriqueDefense> searchHistoriques(String query)  ;

    public List<User> getUsersByRole(Role role)  ;
    /*********************reclamation***********************/
    List<Complaint> getAllComplaint();

    List<Complaint> getAllComplaintByUserId(int userId);

    Optional<Complaint> getComplaintById(int id);

    Complaint createComplaint(Complaint complaint) throws Exception;

    Complaint updateComplaint(Complaint complaint);

    void deleteComplaint(int id);
    /*****************************/
    List<Response> getAllResponse();

    Optional<Response> getResponseById(int id);

    List<Response> getResponseByUserId(int userId);

    List<Complaint> getComplaintsByComplaintIdAndUserId(int idComp, int userId);

    List<Complaint> getComplaintsByUserId(int userId);

    Optional<Response> getResponsesByComplaint(Complaint complaint);

    Response createResponse(Response response);


    Optional<Response> getResponseByComplaintId(int complaintId);

    Response updateResponse(Response response);

    void deleteResponse(int idRep);
}

