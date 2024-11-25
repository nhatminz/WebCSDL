package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Course;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Service.CourseServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    @Autowired
    private CourseServiceImpl courseServiceImpl;
    @Autowired
    private MajorServiceImpl majorServiceImpl;
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/Courses")
    public String showCourseManagement(Model model) {
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("course", new Course());
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("teachers",teacherServiceImpl.getAllTeacher());
        return "Courses";
    }
}
