package br.com.alysongustavoti.backendtodo.service;

import br.com.alysongustavoti.backendtodo.model.Task;
import br.com.alysongustavoti.backendtodo.model.User;
import br.com.alysongustavoti.backendtodo.repository.TaskRepository;
import br.com.alysongustavoti.backendtodo.repository.UserRepository;
import br.com.alysongustavoti.backendtodo.service.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Task createTask(Task task, Integer id){

        Optional<User> userFind = userRepository.findById(id);

        if(userFind.isEmpty()){
            throw new BusinessException("User not found.");
        }

        task.setUser(userFind.get());

        Task taskInsert =  taskRepository.save(task);

        return task;
    }

    public Task findTask(Integer id){

        Optional<Task> taskFind = taskRepository.findById(id);

        if(taskFind.isEmpty()){
            throw new BusinessException("Task not found.");
        }

        return taskFind.get();

    }

    public List<Task> allTaks(){
        return taskRepository.findAll();
    }

    public List<Task> tasksOfUser(User userFind){
        return taskRepository.findTaskByUser(userFind);
    }

    @Transactional
    public Task updateTask(Task task, Integer id){

        Optional<Task> taskUpdate = taskRepository.findById(id);

        if(taskUpdate.isEmpty()){
            throw new BusinessException("Task not found.");
        }

        Task taskConvert = taskUpdate.get();

        BeanUtils.copyProperties(task, taskConvert, "id", "user");

        Task taskUpdated = taskRepository.save(taskConvert);

        return taskUpdated;

    }

    @Transactional
    public Task attachTask(Integer id, Integer taskId){

        Optional<Task> taskUpdateOptional = taskRepository.findById(taskId);
        Optional<Task> taskAttachOptional = taskRepository.findById(id);

        if(taskUpdateOptional.isEmpty()){
            throw new BusinessException("Task not found.");
        }

        if(taskAttachOptional.isEmpty()){
            throw new BusinessException("Task not found.");
        }

        Task taskUpdate = taskUpdateOptional.get();
        Task taskAttach = taskAttachOptional.get();

        taskUpdate.setTask(taskAttach);

        Task taskUpdated = taskRepository.save(taskUpdate);

        return taskUpdated;

    }

    @Transactional
    public Task detachTask(Integer id, Integer taskId){

        Optional<Task> taskUpdateOptional = taskRepository.findById(taskId);
        Optional<Task> taskAttachOptional = taskRepository.findById(id);

        if(taskUpdateOptional.isEmpty()){
            throw new BusinessException("Task not found.");
        }

        if(taskAttachOptional.isEmpty()){
            throw new BusinessException("Task not found.");
        }

        Task taskUpdate = taskUpdateOptional.get();
        Task taskAttach = taskAttachOptional.get();

        if(!taskUpdate.getTask().equals(taskAttach)){
            throw new BusinessException("There is no association between tasks to be disassociated.");
        }

        taskUpdate.setTask(null);

        Task taskUpdated = taskRepository.save(taskUpdate);

        return taskUpdated;

    }

    @Transactional
    public void deleteTask(Integer userId, Integer taskId){

        Optional<User> userFind = userRepository.findById(userId);
        Optional<Task> taskFind = taskRepository.findById(taskId);

        if(userFind.isEmpty()){
            throw new BusinessException("User not found.");
        }

        if(taskFind.isEmpty()){
            throw new BusinessException("Task not found");
        }

        Task task = taskFind.get();
        User user = userFind.get();

       if(!task.getUser().equals(user)){
           throw new BusinessException("You cannot delete an item that does not belong to your user.");
       }

       taskRepository.delete(task);

    }
}
