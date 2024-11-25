package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Service.SchoolClassServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchoolClassController {
    @Autowired
    private SchoolClassServiceImpl schoolClassServiceImpl;
    @GetMapping("/SchoolClasses")
    public String showStudentManagement(Model model) {
        model.addAttribute("schoolClasses", schoolClassServiceImpl.getAllSchoolClass());
        model.addAttribute("schoolClass", new SchoolClass());
        return "SchoolClass";
    }
}
