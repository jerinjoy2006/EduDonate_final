package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.Sell;
import com.edudonate.edudonate.repository.SellRepository;
import org.springframework.stereotype.Service;

@Service
public class SellService {

    private final SellRepository sellRepository;

    public SellService(SellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    public void saveSell(Sell sell) {
        sellRepository.save(sell);
    }
}

