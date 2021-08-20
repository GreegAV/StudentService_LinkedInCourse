package com.example.studentservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String name);

    @Query(value = "Select avg(s.grade) from Student s where s.active=true")
    Double getAvgGradeForActiveStudents();
}
