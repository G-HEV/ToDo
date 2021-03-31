package com.ghev.ToDo.service;


import com.ghev.ToDo.model.Task;
import com.ghev.ToDo.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {


    private final TaskRepo taskrepo;

    @Autowired
    public TaskService(TaskRepo taskrepo){
        this.taskrepo = taskrepo;

    }

    public List<Task> getAllTasks(){
       return  taskrepo.findAll();

    }
    public Optional<Task> getTaskbyId(int id){
        return taskrepo.findById(id);
    }

    public Task addTask(Task task){
        return taskrepo.save(task);

    }

    public void deleteTask(int id){
        taskrepo.deleteById(id);
    }

}
