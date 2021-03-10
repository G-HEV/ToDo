package com.ghev.ToDo.model;


import com.ghev.ToDo.enumeration.ToDoType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private ToDoType type;

    private String description;

    private LocalDate date;

    public Task() {
    }

    public Task(int id, ToDoType type, String description,LocalDate date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date=date;

    }
}
