package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Entity.Teacher;

import java.util.List;

public interface TeacherServices {
    List<Teacher> getAllTeacher();
    void saveTeacher(Teacher teacher);
    Teacher getById(Long id);
    void deleteViaId(Long id);
    List<Teacher> searchTeachers(String keyword);
}
