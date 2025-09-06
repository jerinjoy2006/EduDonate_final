package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.Exchange;
import com.edudonate.edudonate.repository.ExchangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService {
    private final ExchangeRepository repo;

    public ExchangeService(ExchangeRepository repo) {
        this.repo = repo;
    }

    // Create a new exchange listing
    public Exchange createExchange(Exchange exchange) {
        return repo.save(exchange);
    }

    // Get all exchanges
    public List<Exchange> getAll() {
        return repo.findAll();
    }

    // Accept an exchange
    public Exchange acceptExchange(String id, String username) {
        return repo.findById(id).map(ex -> {
            if (ex.getStatus() == Exchange.Status.PENDING) {
                ex.setAcceptedBy(username);
                ex.setStatus(Exchange.Status.ACCEPTED);
                return repo.save(ex);
            }
            return ex;
        }).orElseThrow(() -> new RuntimeException("Exchange not found: " + id));
    }

    // Mark exchange as completed
    public Exchange completeExchange(String id) {
        return repo.findById(id).map(ex -> {
            if (ex.getStatus() == Exchange.Status.ACCEPTED) {
                ex.setStatus(Exchange.Status.COMPLETED);
                return repo.save(ex);
            }
            return ex;
        }).orElseThrow(() -> new RuntimeException("Exchange not found: " + id));
    }
}

