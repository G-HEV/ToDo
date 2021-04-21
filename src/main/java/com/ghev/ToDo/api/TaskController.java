package com.ghev.ToDo.api;


import com.ghev.ToDo.model.Task;
import com.ghev.ToDo.model.TaskType;
import com.ghev.ToDo.service.TaskService;
import com.ghev.ToDo.service.TaskTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/TodoList")
public class TaskController{

    private final TaskService taskService;
    private final TaskTypeService taskTypeService;



    @Autowired
    public TaskController(TaskService taskService, TaskTypeService taskTypeService) {
        this.taskService = taskService;
        this.taskTypeService = taskTypeService;
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

        model.addAttribute("types", taskTypeService.getAllTypeTask());

        return "firstpage";
    }
    /*
        Get task
     */
    @GetMapping("/{type}/{id}")
    public String getTaskbyId(Model model,@PathVariable("type") String type ,@PathVariable("id") Integer id){

        log.info("task: {}",taskService.getTaskbyId(id).get());
        model.addAttribute("task",taskService.getTaskbyId(id).get());

        return "task";
    }

    /*
        Delete Task
     */

    @DeleteMapping("/delete/{type}/{id}")
    public String deleteTask(@PathVariable Integer id,@PathVariable("type") String type){

        taskService.deleteTask(id);

        return "redirect:/TodoList";

    }

    /*
        Add Task
     */

    @GetMapping("/addTask")
    public String getAddTask(@ModelAttribute("newTask") Task newTask, Model model){

        model.addAttribute("taskType",taskTypeService.getAllTypeTask());
        log.info("Type task: {}",taskTypeService.getAllTypeTask());



        return "addTask";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("newTask") @Valid Task newTask, BindingResult bindingResult, Model model){



        log.info("new Task: ={} ",newTask);

        if(bindingResult.hasErrors()){

            return "redirect:/TodoList/addTask";
        }

        log.info("new Task: ={} ",newTask);

        taskService.addTask(newTask);

        return "redirect:/TodoList";

    }





    /*

        Complete tasks

    */

    @GetMapping("/completed")
    public String completedListTask(Model model){

    List<Task> taskList = taskService.getAllTasks().stream()
            .filter(task -> task.isDone()==true)
            .collect(Collectors.toList());

        model.addAttribute("doneTask",taskList);
        model.addAttribute("types",taskTypeService.getAllTypeTask());

        return "completeListTask";

    }
    /*
    Create new type Task

     */

    @GetMapping("/new_type")
    public String getNewType(@ModelAttribute("type") TaskType taskType){


        return "newType";
    }

    @PostMapping("/new_type")
    public String postNewType(@ModelAttribute("type") TaskType taskType){

      taskTypeService.addTypeTask(taskType);

        return "redirect:/TodoList" ;

    }

    /*

    Primary Category

     */


    @GetMapping("/{type}")
    public String getTasksWithOtherCategory(Model model,@PathVariable("type") String type){

        log.info("Category: {}",type);


        List<Task> listTaskCategory = taskService.getAllTasks()
                .stream()
                .filter(task -> task.getTask_Type().getTypeTask().equals(type))
                .filter(t -> t.isDone()==false)
                .collect(Collectors.toList());
        log.info("List tasks other Category: {}",listTaskCategory);
        model.addAttribute("tasks",listTaskCategory);
        model.addAttribute("types",taskTypeService.getAllTypeTask());


        return "firstpage";
    }
    /*
    Done task
     */
        @PutMapping("/{id}/done")
        public String doneTask(@PathVariable int id, HttpServletRequest request){

            Task task = taskService.getTaskbyId(id).get();
            task.setDone(true);
            taskService.addTask(task);
            log.info("change task with id {} , task {}",taskService.getTaskbyId(id).get().getId(),taskService.getTaskbyId(id).get());

            String referer = request.getHeader("Referer");


            return "redirect:" + referer;
        }

        /*

        Delete Category handling page
         */
        @GetMapping("/category")
    public String getListCategory(Model model){

            model.addAttribute("categories", taskTypeService.getAllTypeTask());
            return "category";
        }
        @DeleteMapping("category/{id}")
    public String deleteCategory(@PathVariable("id") int id){

            taskTypeService.deleteTypeTaskById(id);
            return "redirect:/TodoList/category";
        }



}
