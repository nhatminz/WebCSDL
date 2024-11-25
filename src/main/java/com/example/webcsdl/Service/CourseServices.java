package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Course;


import java.util.List;

public interface  CourseServices {
    List<Course> getAllCourse();
    void saveCourse(Course course);
    Course getById(Long id);
    void deleteViaId(Long id);
}