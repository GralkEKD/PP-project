package TaskTracker.frontend.Controllers;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.TaskNotFoundException;
import TaskTracker.businessLogic.services.TaskService;
import TaskTracker.database.beans.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TasksController {

    @Autowired
    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    public String listTasks(Model model,
                            @AuthenticationPrincipal UserDetails user) {
        List<Task> tasks = taskService.getTasksOfUser(user.getUsername());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable(name="id") Long id,
                                     @AuthenticationPrincipal UserDetails user){
        ModelAndView editView = new ModelAndView("edit");
        Task task;
        try {
            task = taskService.getTask(id);
        }
        catch (TaskNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if(!task.getCreatorLogin().equals(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        editView.addObject("task", task);
        return editView;
    }

    @PostMapping("/update")
    public String updateTaskSubmit(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }


    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable(name="id") Long id,
                             @AuthenticationPrincipal UserDetails user){

        Task task;
        try {
            task = taskService.getTask(id);
        }
        catch (TaskNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if(!task.getCreatorLogin().equals(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "new";
    }

    @PostMapping("/save")
    public String saveTaskSubmit(@AuthenticationPrincipal UserDetails user,
                                @ModelAttribute Task task) {
        String userName = user.getUsername();
        task.setCreatorLogin(userName);
        task.setCreatorGroupId(0L);
        taskService.addTask(task);
        return "redirect:/tasks";
    }
}