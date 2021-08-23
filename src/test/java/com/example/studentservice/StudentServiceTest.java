package com.example.studentservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @Test
    void getStudentById_forSavedStudent_isReturned(){
        //given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        //when
        Student student=studentService.getStudentById(savedStudent.getId());


        //then
        then(student.getName()).isEqualTo("Mark");
        then(student.getId()).isNotNull();


    }

}
