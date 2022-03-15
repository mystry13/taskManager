package com.myproj.taskmanager.tasks;

import com.myproj.taskmanager.notes.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;
    private LocalDate dueDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean status;

    public TaskEntity(String taskName) {
        this.name = taskName;
        this.dueDate = LocalDate.now().plus(7, ChronoUnit.DAYS);
        this.status = false;
    }
}
