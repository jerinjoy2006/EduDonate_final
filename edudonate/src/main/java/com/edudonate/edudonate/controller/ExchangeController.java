package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Exchange;
import com.edudonate.edudonate.service.ExchangeService;
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

    /* ---------------- Thymeleaf Endpoints ---------------- */

    // Show all exchanges (browse listings)
    @GetMapping("/browse")
    public String browseExchanges(Model model) {
        model.addAttribute("exchanges", service.getAll());
        return "exchanges"; // loads exchanges.html
    }

    // Show form to create a new exchange
    @GetMapping("/new")
    public String showNewExchangeForm(Model model) {
        model.addAttribute("exchange", new Exchange());
        return "new-exchange"; // loads new-exchange.html
    }

    // Handle form submission (create listing)
    @PostMapping
    public String handleNewExchange(@ModelAttribute Exchange exchange) {
        service.createExchange(exchange);
        return "redirect:/exchange/browse";
    }

    // Accept an exchange
    @PostMapping("/accept/{id}")
    public String acceptExchange(@PathVariable String id) {
        service.acceptExchange(id, "VisitorUser"); // Replace "VisitorUser" with logged-in user later
        return "redirect:/exchange/browse";
    }

    /* ---------------- REST API (optional for testing in Postman) ---------------- */

    // Get all exchanges as JSON
    @GetMapping("/api")
    @ResponseBody
    public java.util.List<Exchange> getAllExchangesApi() {
        return service.getAll();
    }

    // Create exchange via JSON (API)
    @PostMapping("/api")
    @ResponseBody
    public Exchange createExchangeApi(@RequestBody Exchange exchange) {
        return service.createExchange(exchange);
    }

    // Accept exchange via API
    @PutMapping("/api/{id}/accept")
    @ResponseBody
    public Exchange acceptExchangeApi(@PathVariable String id) {
        return service.acceptExchange(id, "ApiUser");
    }
}
