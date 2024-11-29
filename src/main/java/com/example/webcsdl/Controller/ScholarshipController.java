package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.MajorServiceImpl;
import com.example.webcsdl.Service.ScholarshipServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ScholarshipController {
    @Autowired
    private ScholarshipServiceImpl scholarshipServiceImpl;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private MajorServiceImpl majorServiceImpl;

    @GetMapping("/Scholarship")
    public String showScholarshipManagement(Model model) {
        model.addAttribute("scholarships", scholarshipServiceImpl.getAllScholarship());
        model.addAttribute("scholarship", new ScholarshipDto());
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "Scholarship";
    }

    @PostMapping("/Scholarships/add")
    public String addScholarship(@ModelAttribute("scholarship") ScholarshipDto scholarshipDto) {
        scholarshipServiceImpl.saveScholarship(toEntity(scholarshipDto));
        return "redirect:/Scholarship";
    }

    @GetMapping("/Scholarship/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Scholarship scholarship = scholarshipServiceImpl.getById(id);
        if (scholarship == null) {
            throw new RuntimeException("Scholarship not found with ID: " + id);
        }
        ScholarshipDto scholarshipDto = toDto(scholarship);
        model.addAttribute("scholarshipDto", scholarshipDto);
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "updateScholarshipForm";
    }

    private ScholarshipDto toDto(Scholarship scholarship) {
        ScholarshipDto scholarshipDto = new ScholarshipDto();
        scholarshipDto.setId(scholarship.getId());
        scholarshipDto.setScholarshipName(scholarship.getScholarshipName());
        scholarshipDto.setAmount(scholarship.getAmount());
        scholarshipDto.setStudentId(scholarship.getStudent() != null ? scholarship.getStudent().getId() : null);
        return scholarshipDto;
    }

    @PostMapping("/Scholarship/save")
    public String updateScholarship(@ModelAttribute("scholarshipDto") ScholarshipDto scholarshipDto) {
        Scholarship scholarship = toEntity(scholarshipDto);
        scholarshipServiceImpl.saveScholarship(scholarship);
        System.out.println("success: " + scholarship);
        return "redirect:/Scholarship";
    }

    @GetMapping("/Scholarship/search")
    public ResponseEntity<List<ScholarshipDto>> searchScholarships(@RequestParam String query) {
        List<Scholarship> scholarships = scholarshipServiceImpl.searchScholarships(query);
        List<ScholarshipDto> scholarshipDtos = scholarships.stream().map(this::toDto).toList();
        return ResponseEntity.ok(scholarshipDtos);
    }

    @GetMapping("/deleteScholarship/{id}")
    public String deleteScholarshipById(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            scholarshipServiceImpl.deleteViaId(id);
            redirectAttributes.addFlashAttribute("message", "Scholarship deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete scholarship with ID: " + id);
        }
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
