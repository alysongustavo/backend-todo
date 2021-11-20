package br.com.alysongustavoti.backendtodo.resource;

import br.com.alysongustavoti.backendtodo.model.Task;
import br.com.alysongustavoti.backendtodo.model.User;
import br.com.alysongustavoti.backendtodo.service.TaskService;
import br.com.alysongustavoti.backendtodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;


    @GetMapping
    public ResponseEntity<?> allTasks(){

        List<Task> listTask = taskService.allTaks();

        if(listTask != null && listTask.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(listTask);
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findTask(@PathVariable("id") Integer userId){

        Task task = taskService.findTask(userId);

        if(task != null){
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}/attach/{taskId}")
    public ResponseEntity<?> attachTask(@PathVariable("id") Integer id,
                                            @PathVariable("taskId") Integer taskId){

        Task taskSave = taskService.attachTask(id, taskId);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);

    }

    @GetMapping(value = "/{id}/detach/{taskId}")
    public ResponseEntity<?> detachTask(@PathVariable("id") Integer id,
                                        @PathVariable("taskId") Integer taskId){

        Task taskSave = taskService.detachTask(id, taskId);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);

    }
}
