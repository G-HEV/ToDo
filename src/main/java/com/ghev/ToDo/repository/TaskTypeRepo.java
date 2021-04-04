package com.ghev.ToDo.repository;

import com.ghev.ToDo.model.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepo extends JpaRepository<TaskType,Integer> {

}
