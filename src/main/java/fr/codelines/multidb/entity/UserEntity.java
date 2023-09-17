package fr.codelines.multidb.entity;

import fr.codelines.multidb.annotation.*;

@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "idUser", nullable = false)
    private Long id;
    @Column(name = "prenom", nullable = false)
    private String firstName;
    @Column(name = "nom", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;


    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
