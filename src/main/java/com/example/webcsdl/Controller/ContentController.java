package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.ClassRoom;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ContentController {
    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    @GetMapping("/req/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/index")
    public String home() {
        return "Dashboard";
    }

    @GetMapping("/ClassManagement")
    public String classManagement(Model model) {
        model.addAttribute("classRooms", classRoomService.findAll());
        return "ClassManagement"; // Trả về tệp class-management.html
    }

    @PostMapping("/req/ClassManagement/search")
    public String searchClass(@RequestParam String className, Model model) {
        Optional<ClassRoom> classRoom = classRoomService.findByName(className);
        model.addAttribute("classRoom", classRoom.orElse(null));
        return "ClassManagement"; // Trả về tệp class-management.html
    }

    @PostMapping("/req/ClassManagement/add-student")
    public String addStudent(@RequestParam String className, @RequestParam String studentName, @RequestParam String studentId) {
        Student student = new Student(studentName, studentId);
        classRoomService.addStudentToClass(className, student);
        return "redirect:/req/ClassManagement";
    }

    @PostMapping("/req/ClassManagement/remove-student")
    public String removeStudent(@RequestParam String className, @RequestParam String studentId) {
        classRoomService.removeStudentFromClass(className, studentId);
        return "redirect:/ClassManagement";
    }
}
