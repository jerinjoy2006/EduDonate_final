package com.edudonate.edudonate.repository;

import com.edudonate.edudonate.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
