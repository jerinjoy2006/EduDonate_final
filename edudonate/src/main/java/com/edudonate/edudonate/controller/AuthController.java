package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.User;
import com.edudonate.edudonate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String registered,
                                @RequestParam(required = false) String logout,
                                Model model) {
        if (error != null) model.addAttribute("errorMessage", "Invalid username or password");
        if (registered != null) model.addAttribute("successMessage", "Registration successful! Please login.");
        if (logout != null) model.addAttribute("successMessage", "You have logged out successfully.");
        return "login";  // templates/login.html
    }

    // Show registration page
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Process registration form
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes redirectAttributes) {
        User newUser = new User(username, password, "ROLE_USER"); // use ROLE_USER
        boolean success = userService.registerUser(newUser);

        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists!");
            return "redirect:/register";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
        return "redirect:/login?registered=true";
    }
}