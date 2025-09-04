package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.Donation;
import com.edudonate.edudonate.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    // ✅ Get all donations
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // ✅ Save a new donation
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    // ✅ Search donations by keyword + type
    public List<Donation> searchDonations(String keyword, String type) {
        if (keyword != null && !keyword.isEmpty() && type != null && !type.isEmpty()) {
            return donationRepository.findByItemTypeContainingIgnoreCaseAndDescriptionContainingIgnoreCase(type, keyword);
        } else if (type != null && !type.isEmpty()) {
            return donationRepository.findByItemTypeContainingIgnoreCase(type);
        } else if (keyword != null && !keyword.isEmpty()) {
            return donationRepository.findByDescriptionContainingIgnoreCaseOrDonorNameContainingIgnoreCase(keyword, keyword);
        } else {
            return donationRepository.findAll();
        }
    }

    // ✅ Get latest donations (e.g., last 5)
    public List<Donation> getLatestDonations(int limit) {
        return donationRepository.findTop5ByOrderByIdDesc();
    }
}
