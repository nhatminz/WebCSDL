package com.example.webcsdl.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ContentController {
    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    @GetMapping("/req/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/current-user")
    @ResponseBody
    public String getCurrentUsername(Principal principal) {
        return principal.getName();
    }

//    @GetMapping("/Dashboard")
//    public String home() {
//        return "Dashboard";
//    }
}
