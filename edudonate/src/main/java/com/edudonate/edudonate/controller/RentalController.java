package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Rental;
import com.edudonate.edudonate.model.RentalStatus;
import com.edudonate.edudonate.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // ✅ List all rentals
    @GetMapping
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        model.addAttribute("rental", new Rental()); // for form
        model.addAttribute("rentalStatus", RentalStatus.class); // ✅ added to fix Thymeleaf enum parsing
        return "rentals";
    }

    // ✅ Show "Add Rental" form
    @GetMapping("/new")
    public String showAddRentalForm(Model model) {
        Rental rental = new Rental();
        rental.setStatus(RentalStatus.AVAILABLE); // default value
        model.addAttribute("rental", rental);
        model.addAttribute("rentalStatus", RentalStatus.class);
        return "add-rental";
    }

    // ✅ Add new rental
    @PostMapping
    public String addRental(@ModelAttribute Rental rental) {
        rentalService.saveRental(rental);
        return "redirect:/rentals?success=true";
    }

    // ✅ Update rental
    @PostMapping("/update/{id}")
    public String updateRental(@PathVariable Long id, @ModelAttribute Rental rental) {
        rentalService.updateRental(id, rental);
        return "redirect:/rentals";
    }

    // ✅ Delete rental
    @GetMapping("/delete/{id}")
    public String deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals?deleted=true";
    }

    // ✅ Show request form (request-rental.html) for a rental
    @GetMapping("/request/{id}")
    public String showRequestForm(@PathVariable Long id, Model model) {
        Rental rental = rentalService.getRentalById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rental Id:" + id));

        model.addAttribute("rental", rental);
        return "request-rental";
    }

    // ✅ Handle request form submission
    @PostMapping("/request/{id}")
    public String handleRequest(@PathVariable Long id, @ModelAttribute Rental rentalRequest) {
        Rental rental = rentalService.getRentalById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rental Id:" + id));

        rental.setRentedTo(rentalRequest.getRentedTo());
        rental.setStartDate(rentalRequest.getStartDate());
        rental.setEndDate(rentalRequest.getEndDate());
        rental.setActive(true);
        rental.setStatus(RentalStatus.ACTIVE); // mark as ACTIVE when requested

        rentalService.saveRental(rental);
        return "redirect:/rentals";
    }
}







