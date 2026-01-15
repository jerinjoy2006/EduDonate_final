package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Exchange;
import com.edudonate.edudonate.service.ExchangeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService service;

    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    // Browse page (lists all)
    @GetMapping("/browse")
    public String browseExchanges(Model model) {
        model.addAttribute("exchanges", service.getAll());
        return "exchanges";
    }

    // Show form to create new listing
    @GetMapping("/new")
    public String showNewExchangeForm(Model model) {
        model.addAttribute("exchange", new Exchange());
        return "new-exchange";
    }

    // Handle form submit (create)
    @PostMapping
    public String handleNewExchange(@ModelAttribute Exchange exchange) {
        service.createExchange(exchange);
        return "redirect:/exchange/browse?success=true";
    }

    // Accept (POST) — uses Authentication to get logged-in username if available
    @PostMapping("/accept/{id}")
    public String acceptExchange(@PathVariable String id, Authentication authentication) {
        String username = (authentication != null && authentication.isAuthenticated())
                ? authentication.getName() : "VisitorUser";
        service.acceptExchange(id, username);
        return "redirect:/exchange/browse?updated=true";
    }

    // Optional: mark completed
    @PostMapping("/complete/{id}")
    public String completeExchange(@PathVariable String id) {
        service.completeExchange(id);
        return "redirect:/exchange/browse?updated=true";
    }

    // Delete exchange
    @GetMapping("/delete/{id}")
    public String deleteExchange(@PathVariable String id) {
        service.deleteExchange(id);
        return "redirect:/exchange/browse?deleted=true";
    }
}