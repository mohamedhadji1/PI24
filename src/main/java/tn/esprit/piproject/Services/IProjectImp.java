package tn.esprit.piproject.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.piproject.Entities.Documents;
import tn.esprit.piproject.Entities.Internship;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Repositories.DocumentsRepository;
import tn.esprit.piproject.Repositories.InternshipRepository;
import tn.esprit.piproject.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class IProjectImp implements IProjectService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private DocumentsRepository documentsRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
/*********************************************************/
    @Override
    public List<Internship> getAllinternships() {
        return internshipRepository.findAll();
    }

    @Override
    public Optional<Internship> getinternshipById(int id) {
        return internshipRepository.findById(id);
    }

    @Override
    public Internship createInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public Internship updateinternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public void deleteinternship(int id) {
        internshipRepository.deleteById(id);
    }

    @Override
    public List<Documents> getAlldocuments() {
        return documentsRepository.findAll();
    }

    @Override
    public Optional<Documents> getdocumentsById(int id) {
        return documentsRepository.findById(id);
    }

    @Override
    public Documents createdocuments(Documents documents) {
        return documentsRepository.save(documents);
    }



    @Override
    public Documents updatedocuments(Documents documents) {
        return documentsRepository.save(documents);
    }

    @Override
    public void deletedocuments(int id) {
        documentsRepository.deleteById(id);

    }
    /***************************************************/
    /*
    @Override
    public List<Documents> getAllDocuments() {
        return documentsRepository.findAll();
    }

    @Override
    public Optional<Documents> getDocumentsById(int id) {
        return DocumentsRepository.findById(id);
    }

    @Override
    public User createDocument(Documents documents) {
        return documentsRepository.save(documents);
    }

    @Override
    public User updateDocuments(Documents documents) {
        return userRepository.save(documents);
    }

    @Override
    public void deleteDocuments(int id) {
        documentsRepository.deleteById(id);
    }*/
}
