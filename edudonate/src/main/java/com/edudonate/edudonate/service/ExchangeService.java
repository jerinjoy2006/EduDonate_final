package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.Exchange;
import com.edudonate.edudonate.repository.ExchangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService {
    private final ExchangeRepository repo;

    // Constructor Injection (best practice in Spring)
    public ExchangeService(ExchangeRepository repo) {
        this.repo = repo;
    }

    // Create a new exchange request
    public Exchange createExchange(Exchange exchange) {
        return repo.save(exchange);
    }

    // Get all exchanges involving a specific user
    public List<Exchange> getUserExchanges(String username) {
        return repo.findByFromUserOrToUser(username, username);
    }

    // Update the status of an exchange
    public Exchange updateStatus(String id, Exchange.Status status) {
        return repo.findById(id).map(e -> {
            e.setStatus(status);
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Exchange not found: " + id));
    }

    // Get all exchange requests
    public List<Exchange> getAll() {
        return repo.findAll();
    }
}

