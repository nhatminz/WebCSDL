package com.example.webcsdl.Controller;

import com.example.webcsdl.Service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping("/Department")
    public String Department(Model model) {
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Department";
    }
}
