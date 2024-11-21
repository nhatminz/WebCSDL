package com.example.webcsdl.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/Dashboard")
    public String home() {
        return "Dashboard";
    }
}
