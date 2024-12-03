package com.example.webcsdl.Controller;

import com.example.webcsdl.Service.DashboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private DashboardServiceImpl dashboardServiceImpl;

    @GetMapping("/Dashboard")
    public String home(Model model) {
        model.addAttribute("studentCount", dashboardServiceImpl.getStudentCount());
        model.addAttribute("courseCount", dashboardServiceImpl.getCourseCount());
        model.addAttribute("teacherCount", dashboardServiceImpl.getTeacherCount());
        model.addAttribute("schoolClassCount", dashboardServiceImpl.getSchoolClassCount());
        return "Dashboard";
    }

}

