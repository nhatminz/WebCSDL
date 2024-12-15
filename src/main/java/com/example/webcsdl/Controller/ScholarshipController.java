package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScholarshipController {
    @Autowired
    private ScholarshipServiceImpl scholarshipServiceImpl;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private MajorServiceImpl majorServiceImpl;

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private ScholarshipServices scholarshipServices;

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

    @GetMapping("/Scholarships/update/{id}")
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
        scholarshipDto.setStudentName(scholarship.getStudent().getFirstName() + " " + scholarship.getStudent().getLastName());
        return scholarshipDto;
    }

    @PostMapping("/Scholarship/save")
    public String updateScholarship(@ModelAttribute("scholarshipDto") ScholarshipDto scholarshipDto) {
        Scholarship scholarship = toEntity(scholarshipDto);
        scholarshipServiceImpl.saveScholarship(scholarship);
        System.out.println("success: " + scholarship);
        return "redirect:/Scholarship";
    }

    @GetMapping("/Scholarships/search")
    @ResponseBody
    public List<ScholarshipDto> searchScholarships(@RequestParam("query") String query) {
        List<Scholarship> scholarships  = scholarshipServiceImpl.searchScholarships(query); // searchMajors là hàm tìm kiếm trong service
        return scholarships.stream().map(this::toDto).toList();
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
    @GetMapping("/exportScholarships")
    public ResponseEntity<byte[]> exportScholarships() {
        List<Scholarship> scholarships = scholarshipServices.getAllScholarship();

        List<ScholarshipDto> scholarshipDtos = scholarships.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        byte[] pdfBytes = pdfExportService.exportScholarshipsToPdf(scholarshipDtos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "scholarships.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
