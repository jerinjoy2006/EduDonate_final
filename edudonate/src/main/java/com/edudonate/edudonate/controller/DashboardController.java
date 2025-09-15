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

    // ✅ Dashboard (main page after login)
    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        model.addAttribute("title", "EduDonate Dashboard");
        return "dashboard"; // dashboard.html
    }

    // ✅ Donations page
    @GetMapping("/donations-page")
    public String donationsPage(Model model) {
        List<Donation> donations = donationService.getAllDonations();
        model.addAttribute("donations", donations);
        return "donations"; // donations.html
    }

    // ✅ Rent page
    @GetMapping("/rent-page")
    public String rentPage(Model model) {
        model.addAttribute("title", "Rent Items");
        return "rent"; // rent.html
    }

    // ✅ Exchange page
    @GetMapping("/exchange-page")
    public String exchangePage(Model model) {
        model.addAttribute("title", "Exchange Items");
        return "exchanges"; // exchange.html
    }
}
