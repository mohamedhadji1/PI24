package tn.esprit.piproject.Entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
/*import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;*/



@Document(collection = "users")
public class User {

    private int id;

   /* @NotBlank*/
    private String name;

    /*@NotBlank*/
    private String lastName;

   /* @NotBlank
    @Email*/
    private String email;

    private ERole ERole;
    private Role roles;

    private String address;

    /*@NotBlank
    @Size(min = 6, max = 255)*/
    private String password;



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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public ERole getRole() {
        return ERole;
    }

    public void setRole(ERole ERole) {
        this.ERole = ERole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
