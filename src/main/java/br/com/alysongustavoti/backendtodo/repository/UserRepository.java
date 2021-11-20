package br.com.alysongustavoti.backendtodo.repository;

import br.com.alysongustavoti.backendtodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
