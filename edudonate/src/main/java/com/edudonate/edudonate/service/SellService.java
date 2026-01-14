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

    // Ensure the method name is saveSell (to match the controller call)
    public void saveSell(Sell sell) {
        sellRepository.save(sell);
    }

    public java.util.List<Sell> getAllSells() {
        return sellRepository.findAll();
    }

    public void deleteSellById(Long id) {
        sellRepository.deleteById(id);
    }
}