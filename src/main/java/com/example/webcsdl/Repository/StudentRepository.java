package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Student> findByStudentClass(SchoolClass schoolClass);

}