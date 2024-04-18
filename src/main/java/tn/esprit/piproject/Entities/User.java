package tn.esprit.piproject.Entities;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/*import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;*/



@Document(collection = "users")
public class User {
    @Id
    @Field(name = "_id")
    private String id;

   /* @NotBlank*/
    private String name;

    /*@NotBlank*/
    private String lastName;

   /* @NotBlank
    @Email*/
    private String email;

    private ERole ERole;

    private String address;

    /*@NotBlank
    @Size(min = 6, max = 255)*/
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();


  
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    public User(String name, String lastName,
                String address, String email, Set<Role> roles) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.roles = roles;
    }

	public User(String id, String name, String lastName, String email, tn.esprit.piproject.Entities.ERole eRole,
			String address, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		ERole = eRole;
		this.address = address;
		this.password = password;
		this.roles = roles;
	}

	public User(String username, String email, String password) {
        this.name = username;
        this.email = email;
        this.password = password;
      }

	public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public Set<Role> getRoles() {
      return roles;
    }

    public void setRoles(Set<Role> roles) {
      this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
