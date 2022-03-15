package com.myproj.taskmanager.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTaskRequestBody {
    String name;
    Long id;
    LocalDate dDate;
    Boolean status;
}
