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

    public Donation addDonation(Donation donation) {
        donation.setAvailable(true);
        return donationRepository.save(donation);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public List<Donation> getDonationsByType(String type) {
        return donationRepository.findByItemType(type);
    }

    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    public Donation markAsUnavailable(Long id) {
        Donation donation = getDonationById(id);
        if (donation != null) {
            donation.setAvailable(false);
            return donationRepository.save(donation);
        }
        return null;
    }
}