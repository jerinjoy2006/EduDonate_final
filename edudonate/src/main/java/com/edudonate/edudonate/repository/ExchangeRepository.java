package com.edudonate.edudonate.repository;

import com.edudonate.edudonate.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, String> {
    List<Exchange> findByStatus(Exchange.Status status); // optional helper
}
