package com.edudonate.edudonate.repository;

import com.edudonate.edudonate.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {
    // No additional methods needed for now
}

