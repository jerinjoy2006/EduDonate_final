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
        return "exchanges"; // loads templates/exchanges.html
    }

    // Fallback for /exchange/list → redirects to /exchange/browse
    @GetMapping("/list")
    public String listRedirect() {
        return "redirect:/exchange/browse";
    }

    // Show form to create a new exchange
    @GetMapping("/new")
    public String showNewExchangeForm(Model model) {
        model.addAttribute("exchange", new Exchange());
        return "new-exchange"; // loads templates/new-exchange.html
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
        service.acceptExchange(id, "VisitorUser"); // Replace with logged-in user later
        return "redirect:/exchange/browse";
    }

    /* ---------------- REST API (optional for Postman testing) ---------------- */

    @GetMapping("/api")
    @ResponseBody
    public java.util.List<Exchange> getAllExchangesApi() {
        return service.getAll();
    }

    @PostMapping("/api")
    @ResponseBody
    public Exchange createExchangeApi(@RequestBody Exchange exchange) {
        return service.createExchange(exchange);
    }

    @PutMapping("/api/{id}/accept")
    @ResponseBody
    public Exchange acceptExchangeApi(@PathVariable String id) {
        return service.acceptExchange(id, "ApiUser");
    }
}
