package com.edudonate.edudonate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")   // root URL → homepage
    public String homePage() {
        return "home"; // loads home.html
    }

    @GetMapping("/home") // optional: /home also works
    public String homeAlias() {
        return "home";
    }
}

