package com.example.webcsdl.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/Dashboard")
    public String home() {
        return "Dashboard";
    }

    @GetMapping("/Teachers")
    public String showTeachersManagement() {
        return "Teachers";
    }

    @GetMapping("/Classes")
    public String showClassManagement() {
        return "Classes";
    }

    @GetMapping("/Courses")
    public String showCourseManagement() {
        return "Courses";
    }
}

