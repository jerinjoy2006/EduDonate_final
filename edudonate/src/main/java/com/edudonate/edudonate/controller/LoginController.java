package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.User;
import com.edudonate.edudonate.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // templates/login.html
    }

    // Process login form
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        if (userService.validateUser(username, password)) {
            session.setAttribute("user", username);
            return "redirect:/welcome";
        }
        return "redirect:/login?error=true";
    }


    // Show registration page
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Process registration form
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {
        User newUser = new User(username, password, "USER");
        userService.saveUser(newUser);
        return "redirect:/login?registered=true";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
