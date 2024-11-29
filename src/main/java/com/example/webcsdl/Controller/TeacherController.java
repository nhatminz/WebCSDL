package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/Teachers")
    public String showTeacherManagement(Model model) {
        model.addAttribute("teachers", teacherServiceImpl.getAllTeacher());
        model.addAttribute("teacher", new TeacherDto());
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Teachers";
    }

    @PostMapping("/Teachers/add")
    public String addTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherServiceImpl.saveTeacher(toEntity(teacherDto));
        return "redirect:/Teachers";
    }

    @GetMapping("/Teachers/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherServiceImpl.getById(id);
        if (teacher == null) {
            throw new RuntimeException("teacher not found");
        }
        TeacherDto teacherDto = toDto(teacher);
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherDto", teacherDto);
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "updateTeacherForm";
    }

    @PostMapping("/Teachers/save")
    public String updateTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherServiceImpl.saveTeacher(toEntity(teacherDto));
        return "redirect:/Teachers";
    }

    public Teacher toEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());

        Department department = departmentServiceImpl.getById(teacherDto.getDepartmentId());
        teacher.setDepartment(department);
        return teacher;
    }

    public TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setPhoneNumber(teacher.getPhoneNumber());
        teacherDto.setDepartmentId(teacher.getDepartment().getId());
        return teacherDto;
    }
    @GetMapping("/Teachers/search")
    public ResponseEntity<List<TeacherDto>> searchTeachers(@RequestParam String query) {
        List<Teacher> teachers = teacherServiceImpl.searchTeachers(query);
        List<TeacherDto> teacherDtos = teachers.stream().map(this::toDto).toList();
        return ResponseEntity.ok(teacherDtos);
    }

}
