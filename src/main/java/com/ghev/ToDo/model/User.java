package com.ghev.ToDo.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
