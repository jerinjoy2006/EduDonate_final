package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.User;
import com.edudonate.edudonate.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Show registration page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.html
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        boolean success = userService.registerUser(user); // returns true if successful
        if (success) {
            model.addAttribute("successMessage", "Registration successful! Please login.");
            return "login"; // redirect to login with message
        } else {
            model.addAttribute("errorMessage", "Username already exists. Try again.");
            model.addAttribute("user", new User());
            return "register"; // show register page again
        }
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "login"; // login.html
    }

    // Handle login manually
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/dashboard"; // successful login
        } else {
            model.addAttribute("errorMessage", "Invalid username or password!");
            return "login";
        }
    }

    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
