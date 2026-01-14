package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Donation;
import com.edudonate.edudonate.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Donation> getAllDonationsApi() {
        return donationService.getAllDonations();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Donation addDonationApi(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public String deleteDonationApi(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "Donation deleted with id " + id;
    }

    @PutMapping("/api/{id}/unavailable")
    @ResponseBody
    public Donation markAsUnavailable(@PathVariable Long id) {
        return donationService.markAsUnavailable(id);
    }

    @GetMapping
    public String showDonationsPage(@RequestParam(required = false) String type, Model model) {
        List<Donation> donations = (type == null || type.isEmpty())
                ? donationService.getAllDonations()
                : donationService.getDonationsByType(type);

        List<String> itemTypes = Arrays.asList("Books", "Clothes", "Electronics", "Stationery", "Other");

        model.addAttribute("donations", donations);
        model.addAttribute("selectedType", type);
        model.addAttribute("itemTypes", itemTypes);
        return "donations"; // maps to donations.html
    }

    @GetMapping("/new")
    public String showAddDonationForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("itemTypes", Arrays.asList("Books", "Clothes", "Electronics", "Stationery", "Other"));
        return "add-donation"; // maps to add-donation.html
    }

    @PostMapping
    public String saveDonation(@ModelAttribute Donation donation) {
        donationService.addDonation(donation);
        return "redirect:/donations";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "redirect:/donations";
    }
}