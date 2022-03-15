package com.myproj.taskmanager.tasks;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TasksService {
    private TasksRepository tasksRepo;

    /*
     * using constructor we are injecting task-repo, this can be done
     * via @autowired but since that uses reflection it might be slow and prone to few injection errors
     * */
    public TasksService(TasksRepository tasksRepo) {
        this.tasksRepo = tasksRepo;
    }

    List<TaskEntity> getAllTasks() {
        return tasksRepo.findAll();
    }

    TaskEntity addNewTask(String taskName) throws IllegalArgumentException {
        TaskEntity task = new TaskEntity(taskName);
        TaskEntity savedTask = tasksRepo.save(task);
        return savedTask;
    }

    TaskEntity getTaskById(Long id) throws Exception {
        TaskEntity tById = tasksRepo.getById(id);
        return tById;
    }

    void delTaskById(Long id) throws IllegalArgumentException {
        tasksRepo.deleteById(id);
    }

    TaskEntity updateTaskById(Long id, LocalDate date, Boolean status) throws Exception {
        int recUpd = tasksRepo.updTaskById(date, status, id);
        if (recUpd == 0) {
            return null;
        }
        TaskEntity task = getTaskById(id);
        return task;
    }
}
