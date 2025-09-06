package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.service.DonationService;
import com.edudonate.edudonate.model.Donation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final DonationService donationService;

    public DashboardController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        model.addAttribute("title", "EduDonate Dashboard");
        return "dashboard";
    }

    @GetMapping("/donations-page")
    public String donationsPage(Model model) {
        List<Donation> donations = donationService.getAllDonations();
        model.addAttribute("donations", donations);
        return "donations"; // maps to donations.html
    }
}