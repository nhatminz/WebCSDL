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
import java.util.List;

@Controller
public class SchoolClassController {
    @Autowired
    private SchoolClassServiceImpl schoolClassServiceImpl;

    @GetMapping("/Classes")
    public String showSchoolClassManagement(Model model) {
        model.addAttribute("schoolClasses", schoolClassServiceImpl.getAllSchoolClass());
        model.addAttribute("schoolClass", new SchoolClass());
        return "Classes";
    }

    @PostMapping("SchoolClass/add")
    public String addSchoolClass(@ModelAttribute("schoolClass") SchoolClass schoolClass) {
        schoolClassServiceImpl.saveSchoolClass(schoolClass);
        return "redirect:/Classes";
    }

    @DeleteMapping("/SchoolClass/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteSchoolClass(@PathVariable Long id) {
        boolean isDeleted = schoolClassServiceImpl.deleteSchoolClassById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Class not found");
        }
    }

    @GetMapping("/SchoolClass/{id}")
    @ResponseBody
    public ResponseEntity<SchoolClass> getSchoolClass(@PathVariable Long id) {
        SchoolClass schoolClass = schoolClassServiceImpl.getSchoolClassById(id);
        if (schoolClass != null) {
            return ResponseEntity.ok(schoolClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/SchoolClass/update/{id}")
    public String updateSchoolClass(@PathVariable("id") Long id, @ModelAttribute("schoolClass") SchoolClass updatedClass, Model model) {
        SchoolClass existingClass = schoolClassServiceImpl.getSchoolClassById(id);

        if (existingClass != null) {
            existingClass.setClassName(updatedClass.getClassName());
            existingClass.setClassDescription(updatedClass.getClassDescription());
            schoolClassServiceImpl.saveSchoolClass(existingClass);
            model.addAttribute("message", "Class updated successfully");
        } else {
            model.addAttribute("error", "Class not found");
        }
        return "redirect:/Classes";
    }


    @GetMapping("/SchoolClass/search")
    public ResponseEntity<List<SchoolClass>> searchClasses(@RequestParam String query) {
        List<SchoolClass> results = schoolClassServiceImpl.searchClasses(query);
        return ResponseEntity.ok(results);
    }
}
