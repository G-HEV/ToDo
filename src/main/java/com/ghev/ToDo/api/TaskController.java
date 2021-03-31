package com.ghev.ToDo.api;


import com.ghev.ToDo.model.Task;
import com.ghev.ToDo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/TodoList")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

/*
Get list Task
 */
    @GetMapping
    public String getListTasks(Model model){

        List<Task> tasklist = taskService.getAllTasks()
                .stream()
                .filter(task -> task.isDone()==false)
                .collect(Collectors.toList());

        log.info("Task List = {}",tasklist);

        model.addAttribute("tasks",tasklist);

        return "firstpage";
    }
    /*
        Get task
     */
    @GetMapping("/{id}")
    public String getTask(Model model,@PathVariable Integer id){

        log.info("task: ={}",taskService.getTaskbyId(id).get());
        model.addAttribute("task",taskService.getTaskbyId(id).get());

        return "task";
    }

    /*
        Delete Task
     */

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id){

        taskService.deleteTask(id);

        return "redirect:/TodoList";

    }
/*
    Add Task
 */
    @GetMapping("/addTask")
    public String getAddTask(@ModelAttribute("newTask") Task newTask){

        return "addTask";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("newTask") Task newTask, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTask";
        }
        log.info("new Task: ={} ",newTask);
        taskService.addTask(newTask);

        return "redirect:/TodoList";

    }
/*
    Modify Task
 */
    @GetMapping("/editTask/{id}")
    public String getModTask(@PathVariable Integer id, Model model){

        model.addAttribute("modtask",taskService.getTaskbyId(id).get());

        return "addTask";
    }

    @PutMapping("/editTask/{id}")
    public String modTask(@PathVariable int id,Model model,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTask";
        }

        Task task = taskService.getTaskbyId(id).get();

        log.info("edit task: ={}",task);

        model.addAttribute("task",task);

        taskService.addTask(task);

        return "redirect:/TodoList";
    }


    @GetMapping("/completed")
    public String completedListTask(Model model){

        List doneList = taskService.getAllTasks().stream()
                .filter(task -> task.isDone())
                .collect(Collectors.toList());

        log.info("done tasksList = {}",doneList);
        model.addAttribute("doneTask",doneList);


        return "completeListTask";

    }



}
