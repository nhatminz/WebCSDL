package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.ScholarshipServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScholarshipController {
    @Autowired
    private ScholarshipServiceImpl scholarshipServiceImpl;
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/Scholarship")
    public String showScholarshipManagement(Model model) {
        model.addAttribute("scholarships", scholarshipServiceImpl.getAllScholarship());
        model.addAttribute("scholarship", new ScholarshipDto());
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "Scholarship";
    }
    @PostMapping("/Scholarships/add" )
    public String addScholarship(@ModelAttribute("scholarship") ScholarshipDto scholarshipDto) {
        scholarshipServiceImpl.saveScholarship(toEntity(scholarshipDto));
        return "redirect:/Scholarship";
    }
    public Scholarship toEntity(ScholarshipDto scholarshipDto) {
        Scholarship result = new Scholarship();
        result.setId(scholarshipDto.getId());
        result.setScholarshipName(scholarshipDto.getScholarshipName());
        result.setAmount(scholarshipDto.getAmount());

        Student student = studentServiceImpl.getById(scholarshipDto.getStudentId());
        result.setStudent(student);
        return result;
    }
}
