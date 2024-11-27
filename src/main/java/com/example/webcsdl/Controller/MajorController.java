package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.MajorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MajorController {
    @Autowired
    private MajorServiceImpl majorServiceImpl;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Autowired
   public MajorServices majorServices;

    @GetMapping("/Major")
    public String showMajorManagement(Model model) {
        model.addAttribute("Majors", majorServiceImpl.getAllMajor());
        model.addAttribute("major", new Major());
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Major";
    }
}
