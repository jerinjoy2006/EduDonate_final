package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Donation;
import com.edudonate.edudonate.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Show all donations + filter
    @GetMapping("/donations")
    public String showDonations(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String type,
                                Model model) {
        List<Donation> donations;

        if ((keyword != null && !keyword.isEmpty()) || (type != null && !type.isEmpty())) {
            donations = donationService.searchDonations(keyword, type);
        } else {
            donations = donationService.getAllDonations();
        }

        model.addAttribute("donations", donations);
        model.addAttribute("donation", new Donation()); // empty form object
        return "donations";  // must match donations.html
    }

    // Handle new donation form submission
    @PostMapping("/donations/add")
    public String addDonation(@ModelAttribute Donation donation) {
        donationService.saveDonation(donation);
        return "redirect:/donations"; // refresh page with updated list
    }
}
