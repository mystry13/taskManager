package com.myproj.taskmanager.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

//in this line <T, Id> T is the data type of entity and Id is data type of the id (long for id , String for UUID)
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("update tasks t set t.dueDate = :dDate, t.status = :status where t.id = :id")
    int updTaskById(@Param("dDate") LocalDate dDate, @Param("status") Boolean status, @Param("id") Long id);

}
