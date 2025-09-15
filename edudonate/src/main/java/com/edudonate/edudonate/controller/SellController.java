package com.edudonate.edudonate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellController {

    @GetMapping("/sell")
    public String sellPage(Model model) {
        // Add any data to the model if needed later
        return "sell"; // This links to sell.html in the templates folder
    }

    @GetMapping("/add-sell")
    public String addSellPage() {
        return "add-sell"; // This links to add-sell.html in the templates folder
    }
}

