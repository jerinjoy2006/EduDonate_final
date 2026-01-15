package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.Exchange;
import com.edudonate.edudonate.repository.ExchangeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ExchangeService {
    private final ExchangeRepository repo;

    public ExchangeService(ExchangeRepository repo) {
        this.repo = repo;
    }

    // Create new listing
    public Exchange createExchange(Exchange exchange) {
        if (exchange.getId() == null) exchange.setId(UUID.randomUUID().toString());
        if (exchange.getCreatedAt() == null) exchange.setCreatedAt(LocalDateTime.now());
        if (exchange.getStatus() == null) exchange.setStatus(Exchange.Status.PENDING);
        return repo.save(exchange);
    }

    // Get all listings
    public List<Exchange> getAll() {
        return repo.findAll();
    }

    // Accept a listing (sets acceptedBy and status)
    @Transactional
    public Exchange acceptExchange(String id, String username) {
        Exchange ex = repo.findById(id).orElseThrow(() -> new RuntimeException("Exchange not found: " + id));
        if (ex.getStatus() == Exchange.Status.PENDING) {
            ex.setAcceptedBy(username);
            ex.setStatus(Exchange.Status.ACCEPTED);
            return repo.save(ex);
        }
        return ex;
    }

    // Mark completed
    @Transactional
    public Exchange completeExchange(String id) {
        Exchange ex = repo.findById(id).orElseThrow(() -> new RuntimeException("Exchange not found: " + id));
        if (ex.getStatus() == Exchange.Status.ACCEPTED) {
            ex.setStatus(Exchange.Status.COMPLETED);
            return repo.save(ex);
        }
        return ex;
    }

    // Delete exchange
    public void deleteExchange(String id) {
        repo.deleteById(id);
    }
}
