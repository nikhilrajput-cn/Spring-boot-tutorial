package com.Student_Mangement.service;

import com.Student_Mangement.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();

    StudentDTO findById(Long id);

    StudentDTO create(StudentDTO dto);

    StudentDTO update(Long id, StudentDTO dto);

    void delete(Long id);
}
