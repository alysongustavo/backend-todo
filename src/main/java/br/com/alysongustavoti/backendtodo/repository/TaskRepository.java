package br.com.alysongustavoti.backendtodo.repository;

import br.com.alysongustavoti.backendtodo.model.Task;
import br.com.alysongustavoti.backendtodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findTaskByUser(User user);

}
