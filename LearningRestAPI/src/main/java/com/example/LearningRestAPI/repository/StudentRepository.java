package com.example.LearningRestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.LearningRestAPI.enitity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
