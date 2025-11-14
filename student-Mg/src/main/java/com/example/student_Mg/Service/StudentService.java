package com.example.student_Mg.Service;

import java.util.List;
import java.util.Map;

import com.example.student_Mg.DTO.AddStudentRequestDto;
import com.example.student_Mg.DTO.StudentDto;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    Object createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);
}
