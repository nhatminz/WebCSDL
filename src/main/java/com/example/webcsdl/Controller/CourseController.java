package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.CourseServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("course", new CourseDto());
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("teachers",teacherServiceImpl.getAllTeacher());
        return "Courses";
    }

    @PostMapping("/Courses/add")
    public String addCourse(@ModelAttribute("course") CourseDto courseDto) {
        courseServiceImpl.saveCourse(toEntity(courseDto));
        return "redirect:/Courses";
    }

    public Course toEntity(CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setCourseName(courseDto.getCourseName());
        course.setCourseCode(courseDto.getCourseCode());
        course.setCredits(courseDto.getCredits());

        Major major = majorServiceImpl.getById(courseDto.getMajorId());
        course.setMajor(major);

        Teacher teacher = teacherServiceImpl.getById(courseDto.getTeacherId());
        course.setTeacher(teacher);
        return course;
    }
}
