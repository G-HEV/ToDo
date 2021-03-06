package com.ghev.ToDo.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NonNull
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="id_type_task")
    private TaskType task_Type;

    private String title;

    private String description;

    private LocalDate date=LocalDate.now();

    private boolean done = false;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    public Task() {
    }



    public Task(TaskType task_Type, String description, LocalDate date,String title) {

        this.task_Type = task_Type;
        this.description = description;
        this.date=date;
        this.title=title;

    }
}
