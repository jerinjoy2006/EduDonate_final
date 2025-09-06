package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Exchange;
import com.edudonate.edudonate.service.ExchangeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller  // Use @Controller (works for both API + Thymeleaf views)
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService service;

    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    /* ---------------- REST API endpoints ---------------- */

    // Create new exchange via JSON (API)
    @PostMapping("/api")
    @ResponseBody
    public Exchange createExchangeApi(@RequestBody Exchange exchange) {
        return service.createExchange(exchange);
    }

    // Get all exchanges (API)
    @GetMapping("/api")
    @ResponseBody
    public List<Exchange> getAllExchangesApi() {
        return service.getAll();
    }

    // Get all exchanges for a user (API)
    @GetMapping("/api/user/{username}")
    @ResponseBody
    public List<Exchange> getUserExchangesApi(@PathVariable String username) {
        return service.getUserExchanges(username);
    }

    // Update exchange status (API)
    @PutMapping("/api/{id}/status")
    @ResponseBody
    public Exchange updateStatusApi(@PathVariable String id,
                                    @RequestParam Exchange.Status status) {
        return service.updateStatus(id, status);
    }

    /* ---------------- Thymeleaf endpoints ---------------- */

    // Show all exchanges (HTML page)
    @GetMapping("/list")
    public String showExchanges(Model model) {
        model.addAttribute("exchanges", service.getAll());
        return "exchanges";  // loads exchanges.html
    }

    // Show form to create new exchange
    @GetMapping("/new")
    public String showNewExchangeForm() {
        return "new-exchange";  // loads new-exchange.html
    }

    // Handle form submission
    @PostMapping
    public String handleNewExchange(@ModelAttribute Exchange exchange) {
        service.createExchange(exchange);
        return "redirect:/exchange/list";
    }
}

