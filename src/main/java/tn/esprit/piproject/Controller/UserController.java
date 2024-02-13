package tn.esprit.piproject.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.piproject.Entities.User;
import tn.esprit.piproject.Services.IProjectService;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IProjectService iProjectService;
    @PostMapping ("/add")
    public User addChambre(@RequestBody User user) {return iProjectService.createUser(user);}
    @GetMapping("/listes")
    public List<User> getUsers() {return iProjectService.getAllUsers();}
}
