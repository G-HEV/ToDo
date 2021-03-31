package com.ghev.ToDo.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_type_task")
    private TaskType task_Type;

    private String description;

    private LocalDate date;

    private boolean done = false;

    public Task() {
    }

    public Task(TaskType task_Type, String description,LocalDate date) {

        this.task_Type = task_Type;
        this.description = description;
        this.date=date;

    }
}
