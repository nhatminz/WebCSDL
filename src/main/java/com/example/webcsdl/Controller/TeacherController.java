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

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @Autowired
    private TeacherServices teacherServices;

    @Autowired
    private PdfExportService pdfExportService;

    @GetMapping("/Teachers")
    public String showTeacherManagement(Model model) {
        model.addAttribute("teachers", teacherServiceImpl.getAllTeacher());
        model.addAttribute("teacher", new TeacherDto());
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "Teachers";
    }

    @PostMapping("/Teachers/add")
    public String addTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherServiceImpl.saveTeacher(toEntity(teacherDto));
        return "redirect:/Teachers";
    }

    @GetMapping("/Teachers/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherServiceImpl.getById(id);
        if (teacher == null) {
            throw new RuntimeException("teacher not found");
        }
        TeacherDto teacherDto = toDto(teacher);
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherDto", teacherDto);
        model.addAttribute("departments", departmentServiceImpl.getAllDepartment());
        return "updateTeacherForm";
    }

    @PostMapping("/Teachers/save")
    public String updateTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherServiceImpl.saveTeacher(toEntity(teacherDto));
        return "redirect:/Teachers";
    }

    public Teacher toEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());

        Department department = departmentServiceImpl.getById(teacherDto.getDepartment().getId());
        teacher.setDepartment(department);
        return teacher;
    }

    public TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setPhoneNumber(teacher.getPhoneNumber());
        teacherDto.setDepartment(teacher.getDepartment());
        teacherDto.setDepartmentName(teacher.getDepartment().getDepartmentName());
        return teacherDto;
    }

    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacherById(@PathVariable(value = "id") long id) {
        teacherServiceImpl.deleteViaId(id);
        courseServiceImpl.deleteViaId(id);
        return "redirect:/Teachers";
    }

    @GetMapping("/Teachers/search")
    @ResponseBody
    public List<TeacherDto> searchTeachers(@RequestParam("query") String query) {
        List<Teacher> teachers = teacherServiceImpl.searchTeachers(query);
        return teachers.stream().map(this::toDto).toList();
    }

    public TeacherController(PdfExportService pdfExportService, TeacherServices teacherService) {
        this.pdfExportService = pdfExportService;
        this.teacherServices = teacherService;
    }

    @GetMapping("/exportTeachers")
    public ResponseEntity<byte[]> exportTeachers() {
        List<Teacher> teachers = teacherServices.getAllTeacher();
        List<TeacherDto> teacherDtos = teachers.stream()
                .map(this::toDto)
                .toList();

        byte[] pdfBytes = pdfExportService.exportTeachersToPdf(teacherDtos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "teachers.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
