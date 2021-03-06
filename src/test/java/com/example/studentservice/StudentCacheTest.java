package com.example.studentservice;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class StudentCacheTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getStudentById_forMultipleRequests_isRetrivedFromCache(){
        //given
        Long id=123L;
        given(studentRepository.findById(id)).willReturn(Optional.of(new Student(id, "Mark")));



        //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);


        //then
        then(studentRepository).should(times(1)).findById(id);

    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown(){
        //given
         Long id=1234L;

        //when
        Throwable throwable=catchThrowable(()->studentService.getStudentById(id));


        // then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);



    }
}
