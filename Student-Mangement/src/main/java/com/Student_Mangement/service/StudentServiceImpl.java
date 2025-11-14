package com.Student_Mangement.service;

import com.Student_Mangement.dto.StudentDTO;
import com.Student_Mangement.mapper.StudentMapper;
import com.Student_Mangement.model.Student;
import com.Student_Mangement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return repository.findAll().stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
        return StudentMapper.toDto(s);
    }

    @Override
    public StudentDTO create(StudentDTO dto) {
        Student s = StudentMapper.toEntity(dto);
        s.setId(null); // ensure new
        Student saved = repository.save(s);
        return StudentMapper.toDto(saved);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        Student updated = repository.save(existing);
        return StudentMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found: " + id);
        }
        repository.deleteById(id);
    }
}
