package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.CourseServiceImpl;
import com.example.webcsdl.Service.EnrollmentServiceImpl;
import com.example.webcsdl.Service.EnrollmentServices;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnrollmentController {
    @Autowired
    private EnrollmentServiceImpl enrollmentServiceImpl;
    @Autowired
    private CourseServiceImpl courseServiceImpl;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
//    @Autowired
//    public EnrollmentServices enrollmentServices;
    @GetMapping("/Enrollment")
    public String showEnrollmentManagement(Model model) {
        model.addAttribute("enrollments", enrollmentServiceImpl.getAllEnrollments());
        model.addAttribute("enrollment", new EnrollmentDto());
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "Enrollment";
    }

    @PostMapping("/Enrollments/add")
    public String addEnrollment(@ModelAttribute("enrollment") EnrollmentDto enrollmentDto) {
        enrollmentServiceImpl.saveEnrollment(toEntity(enrollmentDto));
        return "redirect:/Enrollment";
    }

    public Enrollment toEntity(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();

        Student student = studentServiceImpl.getById(enrollmentDto.getStudentId());
        enrollment.setStudent(student);

        Course course = courseServiceImpl.getById(enrollmentDto.getCourseId());
        enrollment.setCourse(course);
        EnrollmentId enrollmentId = new EnrollmentId(enrollmentDto.getStudentId(), enrollmentDto.getCourseId());
        enrollment.setId(enrollmentId);
        return enrollment;
    }

}
