package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.User;
import com.edudonate.edudonate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Show login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle register
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login?registered";
    }

    // ❌ Removed dashboard() here to avoid conflict
}
