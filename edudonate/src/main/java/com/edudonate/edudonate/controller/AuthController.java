package com.edudonate.edudonate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }

    // Register page
    @GetMapping("/register")
    public String register() {
        return "register"; // register.html
    }
}
