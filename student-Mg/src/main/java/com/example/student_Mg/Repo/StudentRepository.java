package com.example.student_Mg.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_Mg.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
