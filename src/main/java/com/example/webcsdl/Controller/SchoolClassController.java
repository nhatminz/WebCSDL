package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Service.SchoolClassServiceImpl;
import com.example.webcsdl.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/SchoolClass/update/{id}")
    public String showUpdateClassForm(@PathVariable("id") long id, Model model) {
        SchoolClass schoolClass = schoolClassServiceImpl.getById(id);
        if (schoolClass == null) {
            throw new RuntimeException("School Class not found with ID: " + id);
        }
        model.addAttribute("schoolClass", schoolClass);
        return "updateClassForm";
    }

    @PostMapping("/SchoolClass/save")
    public String updateClass(@ModelAttribute("schoolClass") SchoolClass schoolClass) {
        schoolClassServiceImpl.saveSchoolClass(schoolClass);
        return "redirect:/Classes";
    }

    @GetMapping("/deleteSchoolClass/{id}")
    public String deleteSchoolClassById(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            schoolClassServiceImpl.deleteViaId(id);
            redirectAttributes.addFlashAttribute("message", "School class deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete school class with ID: " + id);
        }
        return "redirect:/Classes";
    }

    @GetMapping("/SchoolClass/search")
    public ResponseEntity<List<SchoolClass>> searchClasses(@RequestParam String query) {
        List<SchoolClass> results = schoolClassServiceImpl.searchClasses(query);  // Gọi từ service
        return ResponseEntity.ok(results);
    }
}
