package com.myproj.taskmanager.tasks;

import com.myproj.taskmanager.Constants;
import com.myproj.taskmanager.tasks.dto.CreateTaskRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
 * controller works with service and service with repo
 * */
@RequestMapping("/tasks")
@RestController
public class TasksController {
    private TasksService taskService;

    public TasksController(TasksService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    ResponseEntity<List<TaskEntity>> getAllTasks() {
        if (taskService.getAllTasks() == null || taskService.getAllTasks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("")
    ResponseEntity<TaskEntity> createTask(@RequestBody CreateTaskRequestBody body) {
        TaskEntity savedTask = null;
        try {
            savedTask = taskService.addNewTask(body.name);
        } catch (IllegalArgumentException e) {
            ResponseEntity.badRequest();
        }
        if (savedTask == null) {
            return ResponseEntity.status(400).body(savedTask);
        }
        return ResponseEntity.created(
                        URI.create(Constants.BASE_URL + "/tasks/" + savedTask.getId()))
                .body(savedTask);
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskEntity> getTaskById(@RequestBody String id) {
        Long tId;
        TaskEntity task = null;
        try {
            tId = Long.valueOf(id);
            task = taskService.getTaskById(tId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TaskEntity> deleteTaskById(@RequestBody String id) {
        Long tId;
        try {
            tId = Long.valueOf(id);
            taskService.delTaskById(tId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<TaskEntity> updateTaskById(@RequestBody CreateTaskRequestBody body) {
        TaskEntity task = null;
        try {
            task = taskService.updateTaskById(body.id, body.dDate, body.status);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }
}
