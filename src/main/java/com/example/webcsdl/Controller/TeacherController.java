package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Entity.Teacher;
import com.example.webcsdl.Entity.TeacherDto;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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


}
