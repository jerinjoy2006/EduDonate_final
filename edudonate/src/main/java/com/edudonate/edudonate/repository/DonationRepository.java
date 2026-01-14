package com.edudonate.edudonate.repository;

import com.edudonate.edudonate.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByItemType(String itemType);
}