package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentServices {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        Student stud = null;
        if (student.isPresent()) {
            stud = student.get();
        } else {
            throw new RuntimeException("Student not found");
        }
        return stud;
    }

    @Override
    public void deleteViaId(Long id) {
        studentRepository.deleteById(id);
    }


}