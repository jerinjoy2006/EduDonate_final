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

    // ✅ Show all exchanges
    @GetMapping("/browse")
    public String browseExchanges(Model model) {
        model.addAttribute("exchanges", service.getAll());
        return "exchanges"; // exchanges.html
    }

    // ✅ Show form for new exchange
    @GetMapping("/new")
    public String showNewExchangeForm(Model model) {
        model.addAttribute("exchange", new Exchange());
        return "new-exchange"; // new-exchange.html
    }

    // ✅ Handle new exchange submission
    @PostMapping
    public String handleNewExchange(@ModelAttribute("exchange") Exchange exchange) {
        service.createExchange(exchange);
        // redirect to browse after saving
        return "redirect:/exchange/browse";
    }

    // ✅ Accept button (changes status + acceptedBy)
    @PostMapping("/accept/{id}")
    public String acceptExchange(@PathVariable String id, Authentication authentication) {
        String username = (authentication != null && authentication.isAuthenticated())
                ? authentication.getName()
                : "VisitorUser";
        service.acceptExchange(id, username);
        return "redirect:/exchange/browse";
    }

    // ✅ Complete button (mark as completed)
    @PostMapping("/complete/{id}")
    public String completeExchange(@PathVariable String id) {
        service.completeExchange(id);
        return "redirect:/exchange/browse";
    }
}
