package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Donation;
import com.edudonate.edudonate.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Show all donations + form
    @GetMapping("/donations")
    public String showDonations(Model model) {
        model.addAttribute("donations", donationService.getAllDonations());
        model.addAttribute("donation", new Donation()); // empty form object
        return "donations";  // must match donations.html
    }

    // Handle form submission
    @PostMapping("/donations/add")
    public String addDonation(@ModelAttribute Donation donation) {
        donationService.saveDonation(donation);
        return "redirect:/donations"; // refresh page with updated list
    }
}
