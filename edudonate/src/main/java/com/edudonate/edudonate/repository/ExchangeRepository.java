package com.edudonate.edudonate.repository;

import com.edudonate.edudonate.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, String> {

    // Find all exchange requests where user is either sender or receiver
    List<Exchange> findByFromUserOrToUser(String fromUser, String toUser);
}

