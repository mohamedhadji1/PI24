package tn.esprit.piproject.Entities;


import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String motdepass;
    private Role role;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMotdepass() {
        return motdepass;
    }

    public void setMotdepass(String motdepass) {
        this.motdepass = motdepass;
    }
}
