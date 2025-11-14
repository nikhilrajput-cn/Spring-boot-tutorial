package com.example.LearningRestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    int id;
    String name;
    String email;
}