package com.edudonate.edudonate.repository;

import com.edudonate.edudonate.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    // ✅ Search by description or donor name
    List<Donation> findByDescriptionContainingIgnoreCaseOrDonorNameContainingIgnoreCase(String desc, String donor);

    // ✅ Search by item type
    List<Donation> findByItemTypeContainingIgnoreCase(String type);

    // ✅ Search by type + description
    List<Donation> findByItemTypeContainingIgnoreCaseAndDescriptionContainingIgnoreCase(String type, String desc);

    // ✅ Latest 5 donations
    List<Donation> findTop5ByOrderByIdDesc();
}
