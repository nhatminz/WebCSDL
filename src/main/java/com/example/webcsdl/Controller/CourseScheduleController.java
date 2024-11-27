package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.ClassroomServiceImpl;
import com.example.webcsdl.Service.CourseScheduleServiceImpl;
import com.example.webcsdl.Service.CourseScheduleServices;
import com.example.webcsdl.Service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;

@Controller
public class CourseScheduleController {
    @Autowired
    private CourseScheduleServiceImpl courseScheduleServiceImpl;
    @Autowired
    private CourseServiceImpl courseServiceImpl;
    @Autowired
    private ClassroomServiceImpl classroomServiceImpl;

    @GetMapping("/CourseSchedule")
    public String showCourseScheduleManagement(Model model) {
        model.addAttribute("courseSchedules", courseScheduleServiceImpl.getAllCourseSchedule());
        model.addAttribute("courseSchedule", new CourseScheduleDto());
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("classrooms", classroomServiceImpl.getAllClassrooms());
        return "CourseSchedule";
    }
    @PostMapping("/CourseSchedules/add")
    public String addCourseSchedule(@ModelAttribute("courseSchedule") CourseScheduleDto courseScheduleDto) {
        courseScheduleServiceImpl.saveCourseSchedule(toEntity(courseScheduleDto));
        return "redirect:/CourseSchedule";
    }
    public CourseSchedule toEntity(CourseScheduleDto dto) {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setId(dto.getId());
        courseSchedule.setStartTime(Time.valueOf(dto.getStartTime()));
        courseSchedule.setEndTime(Time.valueOf(dto.getEndTime()));
        courseSchedule.setDayOfWeek(dto.getDayOfWeek());
        Course course = courseServiceImpl.getById(dto.getCourseId());
        courseSchedule.setCourse(course);

        Classroom classroom = classroomServiceImpl.getById(dto.getClassroomId());
        courseSchedule.setClassroom(classroom);
        return courseSchedule;
    }
}
