package br.com.alysongustavoti.backendtodo;

import br.com.alysongustavoti.backendtodo.config.property.TodoApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TodoApiProperty.class)
public class BackendTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendTodoApplication.class, args);
    }

}
