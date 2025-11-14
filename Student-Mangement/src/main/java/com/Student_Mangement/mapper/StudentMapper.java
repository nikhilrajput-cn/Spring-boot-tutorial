package com.Student_Mangement.mapper;

import com.Student_Mangement.dto.StudentDTO;
import com.Student_Mangement.model.Student;

public class StudentMapper {

    public static StudentDTO toDto(Student s) {
        if (s == null)
            return null;
        return new StudentDTO(s.getId(), s.getName(), s.getEmail());
    }

    public static Student toEntity(StudentDTO dto) {
        if (dto == null)
            return null;
        Student s = new Student();
        s.setId(dto.getId());
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        return s;
    }
}
