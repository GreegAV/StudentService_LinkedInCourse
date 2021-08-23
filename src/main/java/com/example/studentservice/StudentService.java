package com.example.studentservice;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}