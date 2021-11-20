package br.com.alysongustavoti.backendtodo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_task", schema = "public")
public class Task {

    @Id
    @SequenceGenerator(name = "seq_task_id", sequenceName = "seq_task_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_id")
    private Integer id;

    private String title;
    private String description;

    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Task task;

    @OneToMany(mappedBy="task")
    @JsonIgnore
    private List<Task> children;

    private LocalDate dateExecution;
    private LocalDate dateFinalization;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Status status;
    

}
