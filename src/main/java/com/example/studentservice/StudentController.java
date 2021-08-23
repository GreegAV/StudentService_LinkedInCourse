package com.example.studentservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;


    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id){
        return null;
    }
}
