package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping("/Department")
    public String Department(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Department";
    }

    @PostMapping("/Departments/add")
    public String addDepartment(@ModelAttribute("department") Department department) {
        departmentServiceImpl.saveDepartment(department);
        return "redirect:/Department";
    }
}
