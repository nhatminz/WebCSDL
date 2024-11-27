package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.CourseSchedule;

import java.util.List;

public interface CourseScheduleServices {
    List<CourseSchedule> getAllCourseSchedule();
    void saveCourseSchedule(CourseSchedule courseSchedule);
    CourseSchedule getById(Long id);
    void deleteViaId(Long id);
}
