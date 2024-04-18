package tn.esprit.piproject.request;

import tn.esprit.piproject.Entities.ERole;
import tn.esprit.piproject.Entities.Role;

import java.util.Set;

public class UserC {

    /* @NotBlank*/
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /*@NotBlank*/
    private String lastName;

    /* @NotBlank
     @Email*/
    private String email;

    private String address;

    /*@NotBlank
    @Size(min = 6, max = 255)*/
    private String password;

    private Set<String> roles;


}
