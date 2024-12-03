package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Entity.MajorDto;
import com.example.webcsdl.Entity.StudentDto;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.MajorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/Majors/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Major major = majorServiceImpl.getById(id);
        if (major == null) {
            throw new RuntimeException("Major not found");
        }
        MajorDto majorDto = toDto(major);
        model.addAttribute("major", major);
        model.addAttribute("majorDto", majorDto);
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "updateMajorForm";
    }

    @PostMapping("/Majors/save")
    public String updateMajor(@ModelAttribute("major") MajorDto majorDto) {
        majorServiceImpl.saveMajor(toEntity(majorDto));
        return "redirect:/Major";
    }

    @GetMapping("/deleteMajor/{id}")
    public String deleteMajorById(@PathVariable(value = "id") long id) {
        majorServiceImpl.deleteViaId(id);
        return "redirect:/Major";
    }

    @GetMapping("/Major/search")
    public ResponseEntity<List<Major>> searchMajor(@RequestParam String query) {
        List<Major> results = majorServiceImpl.searchMajor(query);
        return ResponseEntity.ok(results);
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

    private static MajorDto toDto(Major major) {
        MajorDto result = new MajorDto();
        result.setId(major.getId());
        result.setMajorName(major.getMajorName());
        result.setDescription(major.getDescription());
        result.setDepartmentId(major.getDepartment().getId());
        return result;
    }
}
