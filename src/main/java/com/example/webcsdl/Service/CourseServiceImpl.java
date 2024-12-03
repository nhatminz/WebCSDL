package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Course;
import com.example.webcsdl.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseServices {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {
   courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        Course cou = null;
        if (course.isPresent()) {
            cou = course.get();
        } else {
            throw new RuntimeException("Course not found");
        }
        return cou;
    }

    @Override
    public List<Course> searchCourses(String keyword, Integer credits) {
        return courseRepository.searchCourses(keyword, credits);
    }

    @Override
    public void deleteViaId(Long id) {
        courseRepository.deleteById(id);
    }
}
