package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Entity.Teacher;
import com.example.webcsdl.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherServices {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        Teacher teach = null;
        if (teacher.isPresent()) {
            teach = teacher.get();
        } else {
            throw new RuntimeException("Teacher not found");
        }
        return teach;
    }

    @Override
    public void deleteViaId(Long id) {
        teacherRepository.deleteById(id);
    }
    public List<Teacher> searchTeachers(String query) {
        return teacherRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }
}
