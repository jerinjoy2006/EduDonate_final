package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Sell;
import com.edudonate.edudonate.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellController {

    @Autowired
    private SellService sellService;

    @GetMapping("/sell")
    public String sellPage(Model model) {
        model.addAttribute("sellItems", sellService.getAllSells());
        return "sell";
    }

    @GetMapping("/sell/delete/{id}")
    public String deleteSellItem(@PathVariable Long id) {
        sellService.deleteSellById(id);
        return "redirect:/sell";
    }

    // 1. Updated: Show form now sends an empty 'Sell' object for data binding
    @GetMapping("/sell/add")
    public String showAddSellItemForm(Model model) {
        model.addAttribute("sellItem", new Sell());
        return "add-sell";
    }

    // 2. NEW: Method to process the form submission and save data
    @PostMapping("/sell/add")
    public String addSellItem(@ModelAttribute("sellItem") Sell sellItem) {
        try {
            // FIX: Calling the correct method name (saveSell)
            sellService.saveSell(sellItem);
            return "redirect:/sell";
        } catch (Exception e) {
            System.err.println("Database save failed for Sell Item: " + e.getMessage());
            return "redirect:/sell/add?error=true";
        }
    }
}