package com.ghev.ToDo.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String role="ROLE_USER";

    private boolean active =true;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
