package com.ghev.ToDo.service;

import com.ghev.ToDo.model.TaskType;
import com.ghev.ToDo.repository.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskTypeService {


     public final TaskTypeRepo taskTypeRepo;

     @Autowired
    public TaskTypeService(TaskTypeRepo taskTypeRepo) {
        this.taskTypeRepo = taskTypeRepo;
    }


  public   List<TaskType>  getTypeTask(){

         return taskTypeRepo.findAll();
    }

    public   Optional<TaskType> getTypeTaskById(int id){

         return taskTypeRepo.findById(id);
    }

    public  TaskType addTypeTask(TaskType TaskType){
         return taskTypeRepo.save(TaskType);
    }

    public void deleteTypeTaskById(int id ){
         taskTypeRepo.deleteById(id);
    }




}
