package com.example.studentservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetStudentByName_returnsStudentDetails() {
        //given
        Student savedStudent = testEntityManager.persistFlushFind(new Student(null, "Mark"));

        //when
        Student student = studentRepository.getStudentByName("Mark");

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());

    }

    @Test
    void getAvgGradeForActiveStudents_calculateAvg() {
        // given
        Student mark = new Student(null, "Mark", true, 80);
        Student susan = new Student(null, "Susan", true, 100);
        Student peter = new Student(null, "Peter", false, 50);
        Arrays.asList(mark, susan, peter).forEach(testEntityManager::persistFlushFind);

        //when
        Double avgGrade = studentRepository.getAvgGradeForActiveStudents();


        //then
        then(avgGrade).isEqualTo(90);

    }
}
