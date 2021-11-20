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
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, HttpServletResponse response) {
        User userSave = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }

    @GetMapping
    public ResponseEntity<?> allUsers(){

        List<User> listUser = userService.allUsers();

        if(listUser != null && listUser.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(listUser);
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}/tasks")
    public ResponseEntity<?> tasksOfUser(@PathVariable("id") Integer id){

        User userFind = userService.findUser(id);

        List<Task> tasks = taskService.tasksOfUser(userFind);

        if(tasks != null && tasks.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        }

        return ResponseEntity.noContent().build();

    }

    @PostMapping(value = "/{id}/tasks")
    public ResponseEntity<?> insertTaskofUser(@RequestBody Task task, @PathVariable("id") Integer userId){

        Task taskSave = taskService.createTask(task, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);

    }

    @PutMapping(value = "/{id}/tasks/{taskId}")
    public ResponseEntity<?> editTaskofUser(@RequestBody Task task, @PathVariable("id") Integer userId,
                                             @PathVariable("taskId") Integer taskId){

        Task taskSave = taskService.updateTask(task, taskId);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskSave);

    }

    @DeleteMapping(value = "/{id}/tasks/{taskId}")
    public ResponseEntity<?> deleteTaskofUser(@PathVariable("id") Integer userId, @PathVariable("taskId") Integer taskId){

        taskService.deleteTask(userId, taskId);

        return ResponseEntity.noContent().build();

    }

}
