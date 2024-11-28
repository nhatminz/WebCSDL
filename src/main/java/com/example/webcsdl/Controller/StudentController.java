package com.example.webcsdl.Controller;


import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Student;

import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.SchoolClassServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import com.example.webcsdl.Service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private MajorServiceImpl majorServiceImpl;

    @Autowired
    private SchoolClassServiceImpl schoolClassServiceImpl;

    @Autowired
    public StudentServices studentServices;

    @GetMapping("/Students")
    public String showStudentManagement(Model model) {
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        model.addAttribute("student", new StudentDto());
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("classes", schoolClassServiceImpl.getAllSchoolClass());
        return "Students";
    }

    @PostMapping("/Students/add")
    public String addStudent(@ModelAttribute("student") StudentDto studentDto) {
        studentServiceImpl.saveStudent(toEntity(studentDto));
        return "redirect:/Students";
    }

    @GetMapping("/Students/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentServiceImpl.getById(id);
        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        model.addAttribute("student", student);
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("classes", schoolClassServiceImpl.getAllSchoolClass());
        return "updateStudentForm";
    }

    @PostMapping("/Students/save")
    public String updateStudent(@ModelAttribute("studentDto") StudentDto studentDto) {
        System.out.println("Updating student with ID: " + studentDto.getId());
        System.out.println("Student DTO: " + studentDto);
        Student student = toEntity(studentDto);
        studentServiceImpl.saveStudent(student);
        return "redirect:/Students";
    }

    public Student toEntity(StudentDto dto) {
        Student result = new Student();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setDateOfBirth(String.valueOf(LocalDate.parse(dto.getDateOfBirth())));
        result.setEmail(dto.getEmail());
        result.setPhoneNumber(dto.getPhoneNumber());
        result.setAddress(dto.getAddress());
        result.setGpa(dto.getGpa());
        // Tìm `Major` từ ID
        Major major = majorServiceImpl.getById(dto.getMajorId());
        result.setMajor(major);

        // Tìm `SchoolClass` từ ID
        SchoolClass schoolClass = schoolClassServiceImpl.getById(dto.getClassId());
        result.setStudentClass(schoolClass);

        return result;
    }
}
