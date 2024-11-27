package com.example.webcsdl.Service;


import com.example.webcsdl.Entity.CourseSchedule;

import com.example.webcsdl.Repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleServices {
   @Autowired
   private CourseScheduleRepository courseScheduleRepository;
    @Override
    public List<CourseSchedule> getAllCourseSchedule() {
        return courseScheduleRepository.findAll();
    }

    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule) {
    courseScheduleRepository.save(courseSchedule);
    }

    @Override
    public CourseSchedule getById(Long id) {
        Optional<CourseSchedule> courseSchedule = courseScheduleRepository.findById(id);
        CourseSchedule coursesh = null;

        if (courseSchedule.isPresent()) {
            coursesh = courseSchedule.get();
        } else {
            throw new RuntimeException("CourseSchedule not found");
        }
        return coursesh;
    }

    @Override
    public void deleteViaId(Long id) {
    courseScheduleRepository.deleteById(id);
    }
}
