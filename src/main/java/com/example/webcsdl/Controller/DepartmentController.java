package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Teacher;
import com.example.webcsdl.Entity.TeacherDto;
import com.example.webcsdl.Service.DepartmentServiceImpl;
import com.example.webcsdl.Service.DepartmentServices;
import com.example.webcsdl.Service.PdfExportService;
import com.example.webcsdl.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private DepartmentServices departmentServices;

    @GetMapping("/Department")
    public String Department(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Department";
    }

    @PostMapping("/Departments/add")
    public String addDepartment(@ModelAttribute("department") Department department) {
        departmentServiceImpl.saveDepartment(department);
        return "redirect:/Department";
    }

    //b3: tạo hàm dướidđây
    @GetMapping("/Department/update/{id}")
    public String showUpdateDepartmentForm(@PathVariable("id") long id, Model model) {
        // Biến để lấy thông tin department muốn sửa
        Department department = departmentServiceImpl.getById(id);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        model.addAttribute("department", department);
        return "updateDepartmentForm";
    }

    @PostMapping("/Department/save")
    public String updateDepartment(@ModelAttribute("department") Department department) {
        departmentServiceImpl.saveDepartment(department);
        return "redirect:/Department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartmentById(@PathVariable(value = "id") long id) {
        departmentServiceImpl.deleteViaId(id);
        return "redirect:/Department";
    }

    @GetMapping("/Department/search")
    public ResponseEntity<List<Department>> searchDepartments(@RequestParam String query) {
        List<Department> results = departmentServiceImpl.searchDepartments(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/departments/export")
    public ResponseEntity<byte[]> exportDepartmentsToPdf() {
        List<Department> departments = departmentServices.getAllDepartment();

        byte[] pdfBytes = pdfExportService.exportDepartmentsToPdf(departments);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=departments.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
