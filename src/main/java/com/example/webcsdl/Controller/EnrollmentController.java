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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EnrollmentController {
    @Autowired
    private EnrollmentServiceImpl enrollmentServiceImpl;
    @Autowired
    private CourseServiceImpl courseServiceImpl;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private PdfExportService pdfExportService;
    @Autowired
    private EnrollmentServices enrollmentServices;

    @GetMapping("/Enrollment")
    public String showEnrollmentManagement(Model model) {
        model.addAttribute("enrollments", enrollmentServiceImpl.getAllEnrollments());
        model.addAttribute("enrollment", new EnrollmentDto());
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "Enrollment";
    }

    @PostMapping("/Enrollments/add")
    public String addEnrollment(@ModelAttribute("enrollment") EnrollmentDto enrollmentDto) {
        enrollmentServiceImpl.saveEnrollment(toEntity(enrollmentDto));
        return "redirect:/Enrollment";
    }

    @GetMapping("/Enrollments/update/{studentId}/{courseId}")
    public String showUpdateForm(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId, Model model) {
        Enrollment enrollment = enrollmentServiceImpl.getById(studentId, courseId);
        if (enrollment == null) {
            throw new RuntimeException("Enrollment not found");
        }
        EnrollmentDto enrollmentDto = toDto(enrollment);
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("enrollmentDto", enrollmentDto);
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("students", studentServiceImpl.getAllStudent());
        return "updateEnrollmentForm";
    }

    @PostMapping("/Enrollments/save")
    public String updateEnrollment(@ModelAttribute("enrollmentDto") EnrollmentDto enrollmentDto) {
        enrollmentServiceImpl.saveEnrollment(toEntity(enrollmentDto));
        return "redirect:/Enrollment";
    }

    @GetMapping("/deleteEnrollment/{studentId}/{courseId}")
    public String deleteEnrollment(@PathVariable("studentId") Long studentId, @PathVariable Long courseId, RedirectAttributes redirectAttributes) {
        try {
            EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
            enrollmentServiceImpl.deleteViaId(enrollmentId);
            redirectAttributes.addFlashAttribute("message", "Enrollment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete enrollment");
        }
        return "redirect:/Enrollment";
    }

    @GetMapping("/Enrollment/search")
    @ResponseBody
    public List<EnrollmentDto> searchEnrollments(@RequestParam("query") String keyword) {
        List<Enrollment> enrollments = enrollmentServiceImpl.searchEnrollments(keyword);
        return enrollments.stream().map(this::toDto).toList();
    }


    public Enrollment toEntity(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();

        Student student = studentServiceImpl.getById(enrollmentDto.getStudentId());
        enrollment.setStudent(student);

        Course course = courseServiceImpl.getById(enrollmentDto.getCourseId());
        enrollment.setCourse(course);
        EnrollmentId enrollmentId = new EnrollmentId(enrollmentDto.getStudentId(), enrollmentDto.getCourseId());
        enrollment.setId(enrollmentId);
        enrollment.setEnrollmentDate(LocalDate.parse(enrollmentDto.getEnrollmentDate()));
        enrollment.setGrade(enrollmentDto.getGrade());
        return enrollment;
    }

    private EnrollmentDto toDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setStudentId(enrollment.getId().getStudentId());
        enrollmentDto.setCourseId(enrollment.getId().getCourseId());
        enrollmentDto.setStudentName(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        enrollmentDto.setGrade(enrollment.getGrade());
        enrollmentDto.setCourseName(enrollment.getCourse().getCourseName());
        enrollmentDto.setEnrollmentDate(enrollment.getEnrollmentDate().toString());
        return enrollmentDto;
    }

    @GetMapping("/exportEnrollments")
    public ResponseEntity<byte[]> exportEnrollments() {
        // Lấy danh sách Enrollment từ service
        List<Enrollment> enrollments = enrollmentServices.getAllEnrollments();

        // Chuyển đổi các Enrollment sang EnrollmentDto
        List<EnrollmentDto> enrollmentDtos = enrollments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        // Xuất danh sách EnrollmentDto ra PDF
        byte[] pdfBytes = pdfExportService.exportEnrollmentsToPdf(enrollmentDtos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "enrollments.pdf");

        // Trả về PDF dưới dạng response
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
