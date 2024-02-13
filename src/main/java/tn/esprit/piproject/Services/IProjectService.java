package tn.esprit.piproject.Services;

import tn.esprit.piproject.Entities.User;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
}
