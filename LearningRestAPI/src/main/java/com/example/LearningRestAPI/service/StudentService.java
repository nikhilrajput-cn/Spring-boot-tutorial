package com.example.LearningRestAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LearningRestAPI.enitity.Student;
import com.example.LearningRestAPI.repository.StudentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Create new student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update student
    public Optional<Student> updateStudent(Long id, Student studentDetails) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();

            if (studentDetails.getName() != null) {
                existingStudent.setName(studentDetails.getName());
            }
            if (studentDetails.getEmail() != null) {
                existingStudent.setEmail(studentDetails.getEmail());
            }
            if (studentDetails.getPhoneNumber() != null) {
                existingStudent.setPhoneNumber(studentDetails.getPhoneNumber());
            }
            if (studentDetails.getAddress() != null) {
                existingStudent.setAddress(studentDetails.getAddress());
            }

            return Optional.of(studentRepository.save(existingStudent));
        }
        return Optional.empty();
    }

    // Delete student
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
