package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.CourseServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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
        model.addAttribute("teachers", teacherServiceImpl.getAllTeacher());
        return "Courses";
    }

    @GetMapping("/Courses/search")
    public ResponseEntity<List<CourseDto>> searchCourses(@RequestParam String query) {
        List<Course> courses = courseServiceImpl.searchCourses(query);
        List<CourseDto> courseDtos = courses.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(courseDtos);
    }

    private CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseCode(course.getCourseCode());
        courseDto.setCredits(course.getCredits());
        courseDto.setMajorName(course.getMajor().getMajorName());
        courseDto.setTeacherName(course.getTeacher().getFirstName() + ' ' + course.getTeacher().getLastName());
        return courseDto;
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
