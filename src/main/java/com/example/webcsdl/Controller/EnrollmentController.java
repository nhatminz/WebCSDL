package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Enrollment;
import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.CourseServiceImpl;
import com.example.webcsdl.Service.EnrollmentServiceImpl;
import com.example.webcsdl.Service.EnrollmentServices;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnrollmentController {
    @Autowired
    private EnrollmentServiceImpl enrollmentServiceImpl;
    @Autowired
    private CourseServiceImpl courseServiceImpl;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    public EnrollmentServices enrollmentServices;
    @GetMapping("/Enrollment")
    public String showEnrollmentManagement(Model model) {
        model.addAttribute("enrollments", enrollmentServiceImpl.getAllEnrollments());
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "Enrollment";
    }
}
