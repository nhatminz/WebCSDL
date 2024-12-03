package com.example.webcsdl.Service;

import com.example.webcsdl.Repository.CourseRepository;
import com.example.webcsdl.Repository.SchoolClassRepository;
import com.example.webcsdl.Repository.StudentRepository;
import com.example.webcsdl.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardServices {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public long getStudentCount() {
        return studentRepository.count();
    }

    @Override
    public long getTeacherCount() {
        return teacherRepository.count();
    }

    @Override
    public long getCourseCount() {
        return courseRepository.count();
    }

    @Override
    public long getSchoolClassCount() {
        return schoolClassRepository.count();
    }
}
