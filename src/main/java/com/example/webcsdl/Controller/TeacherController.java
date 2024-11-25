package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Entity.Teacher;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/Teachers")
    public String showTeacherManagement(Model model) {
        model.addAttribute("teachers", teacherServiceImpl.getAllTeacher());
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Teachers";
    }
}
