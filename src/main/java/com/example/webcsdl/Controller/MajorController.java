package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Entity.MajorDto;
import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.MajorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MajorController {
    @Autowired
    private MajorServiceImpl majorServiceImpl;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping("/Major")
    public String showMajorManagement(Model model) {
        model.addAttribute("majors", majorServiceImpl.getAllMajor());
        model.addAttribute("major", new MajorDto());
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Major";
    }
    @PostMapping("/Majors/add")
    public String addMajor(@ModelAttribute("major") MajorDto majorDto) {
        majorServiceImpl.saveMajor(toEntity(majorDto));
        return "redirect:/Major";
    }

    public Major toEntity(MajorDto majorDto) {
        Major result = new Major();
        result.setId(majorDto.getId());
        result.setMajorName(majorDto.getMajorName());
        result.setDescription(majorDto.getDescription());

        Department department = departmentServiceImpl.getById(majorDto.getDepartmentId());
        result.setDepartment(department);
        return result;
    }
}
