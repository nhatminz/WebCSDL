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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
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
        //dòng này là DTO, để object để nhập thông tin thay vì object cũ, "student" là cái object trong file html
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
        //hàm toDto dùng ở đây
        StudentDto studentDto = toDto(student);
        model.addAttribute("student", student);
        // phải thêm dòng dto này vào
        model.addAttribute("studentDto", studentDto);
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("classes", schoolClassServiceImpl.getAllSchoolClass());
        return "updateStudentForm";
    }

    private StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setDateOfBirth(student.getDateOfBirth().toString());
        studentDto.setEmail(student.getEmail());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        studentDto.setAddress(student.getAddress());
        studentDto.setGpa(student.getGpa());
        studentDto.setClassId(student.getStudentClass().getId());
        studentDto.setMajorId(student.getMajor().getId());
        studentDto.setClassName(student.getStudentClass().getClassName());
        studentDto.setMajorName(student.getMajor().getMajorName());
        return studentDto;
    }

    @PostMapping("/Students/save")
    public String updateStudent(@ModelAttribute("studentDto") StudentDto studentDto) {
        Student student = toEntity(studentDto);
        studentServiceImpl.saveStudent(student);
        return "redirect:/Students";
    }

    @GetMapping("/Student/search")
    @ResponseBody
    public List<StudentDto> searchStudents(@RequestParam("query") String query) {
        List<Student> students = studentServiceImpl.searchStudents(query); // searchMajors là hàm tìm kiếm trong service
        return students.stream().map(this::toDto).toList();
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudentById(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            studentServiceImpl.deleteViaId(id);
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete student with ID: " + id);
        }
        return "redirect:/Students";
    }

    public Student toEntity(StudentDto dto) {
        Student result = new Student();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
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
