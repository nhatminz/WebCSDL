package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.CourseSchedule;
import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.ClassroomServiceImpl;
import com.example.webcsdl.Service.CourseScheduleServiceImpl;
import com.example.webcsdl.Service.CourseScheduleServices;
import com.example.webcsdl.Service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseScheduleController {
    @Autowired
    private CourseScheduleServiceImpl courseScheduleServiceImpl;
    @Autowired
    private CourseServiceImpl courseServiceImpl;
    @Autowired
    private ClassroomServiceImpl classroomServiceImpl;
    @Autowired
    public CourseScheduleServices courseScheduleServices;

    @GetMapping("/CourseSchedule")
    public String showCourseScheduleManagement(Model model) {
        model.addAttribute("courseschedules", courseScheduleServiceImpl.getAllCourseSchedule());
        model.addAttribute("courseschedule", new CourseSchedule());
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("classrooms", classroomServiceImpl.getAllClassrooms());
        return "CourseSchedule";
    }

}
