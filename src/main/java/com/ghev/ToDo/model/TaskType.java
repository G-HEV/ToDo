package com.ghev.ToDo.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity(name = "TASKTYPE")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_type;


    @OneToMany(mappedBy = "task_Type",cascade = CascadeType.ALL)
    private Set<Task> task;

    @Column(name = "TYPETASK")
    private String typeTask;


    public TaskType() {

    }

    public TaskType( String typeTask) {

        this.typeTask = typeTask;
    }
}
