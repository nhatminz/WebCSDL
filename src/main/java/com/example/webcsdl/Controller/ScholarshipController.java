package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Scholarship;
import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.ScholarshipServiceImpl;
import com.example.webcsdl.Service.ScholarshipServices;
import com.example.webcsdl.Service.SchoolClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScholarshipController {
    @Autowired
    private ScholarshipServiceImpl scholarshipServiceImpl;
//    @Autowired
//    public DepartmentServiceImpl departmentServiceImpl;
//    @Autowired
//    public ScholarshipServices scholarshipServices;
    @GetMapping("/Scholarship")
    public String showScholarshipManagement(Model model) {
        model.addAttribute("scholarships", scholarshipServiceImpl.getAllScholarship());
        model.addAttribute("scholarship", new Scholarship());
        //model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Scholarship";
    }

}
