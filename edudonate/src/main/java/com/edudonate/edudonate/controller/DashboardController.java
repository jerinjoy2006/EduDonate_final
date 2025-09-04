package com.edudonate.edudonate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Dashboard after login
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("message", "Welcome to EduDonate Dashboard!");
        return "dashboard"; // dashboard.html
    }
}
