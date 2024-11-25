package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Student;

import java.util.List;

public interface StudentServices {
    List<Student> getAllStudent();
    void saveStudent(Student student);
    Student getById(Long id);
    void deleteViaId(Long id);
}
