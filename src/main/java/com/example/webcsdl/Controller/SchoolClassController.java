package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Service.SchoolClassServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SchoolClassController {
    @Autowired
    private SchoolClassServiceImpl schoolClassServiceImpl;
    @GetMapping("/Classes")
    public String showStudentManagement(Model model) {
        model.addAttribute("schoolClasses", schoolClassServiceImpl.getAllSchoolClass());
        model.addAttribute("schoolClass", new SchoolClass());
        return "Classes";
    }

    @PostMapping("SchoolClass/add")
    public String addSchoolClass(@ModelAttribute("schoolClass") SchoolClass schoolClass) {
        schoolClassServiceImpl.saveSchoolClass(schoolClass);
        return "redirect:/Classes";
    }
}
