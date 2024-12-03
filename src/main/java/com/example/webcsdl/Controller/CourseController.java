package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.CourseServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @ResponseBody
    public List<CourseDto> searchCourses(@RequestParam(value = "query", required = false) String query,
                                                         @RequestParam(value = "credits", required = false) Integer credit) {
        List<Course> courses = courseServiceImpl.searchCourses(query, credit); // searchMajors là hàm tìm kiếm trong service
        return courses.stream().map(this::toDto).toList();
    }

    private CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseCode(course.getCourseCode());
        courseDto.setCredits(course.getCredits());
        courseDto.setMajorName(course.getMajor().getMajorName());
        courseDto.setTeacherName(course.getTeacher().getFirstName() + ' ' + course.getTeacher().getLastName());
        courseDto.setMajorId(course.getMajor().getId());
        courseDto.setTeacherId(course.getTeacher().getId());
        return courseDto;
    }

    @PostMapping("/Courses/add")
    public String addCourse(@ModelAttribute("course") CourseDto courseDto) {
        courseServiceImpl.saveCourse(toEntity(courseDto));
        return "redirect:/Courses";
    }

    @GetMapping("/Courses/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Course course = courseServiceImpl.getById(id);
        if (course == null) {
            throw new RuntimeException("Course not found with ID: " + id);
        }
        //hàm toDto dùng ở đây
        CourseDto courseDto = toDto(course);
        model.addAttribute("course", course);
        // phải thêm dòng dto này vào
        model.addAttribute("courseDto", courseDto);
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("teachers", teacherServiceImpl.getAllTeacher());
        return "updateCourseForm";
    }

    @PostMapping("/Courses/save")
    public String updateCourse(@ModelAttribute("courseDto") CourseDto courseDto) {
        Course course = toEntity(courseDto);
        courseServiceImpl.saveCourse(course);
        return "redirect:/Courses";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourseById(@PathVariable(value = "id") long id) {
        courseServiceImpl.deleteViaId(id);
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
