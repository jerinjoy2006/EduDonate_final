package com.edudonate.edudonate.controller;

import com.edudonate.edudonate.model.Rental;
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

    @GetMapping
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        model.addAttribute("rental", new Rental()); // for form
        return "rentals"; // rentals.html
    }

    @PostMapping
    public String addRental(@ModelAttribute Rental rental) {
        rentalService.saveRental(rental);
        return "redirect:/rentals";
    }

    @GetMapping("/edit/{id}")
    public String editRental(@PathVariable Long id, Model model) {
        Rental rental = rentalService.getRentalById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rental Id:" + id));
        model.addAttribute("rental", rental);
        return "edit-rental"; // edit-rental.html
    }

    @PostMapping("/update/{id}")
    public String updateRental(@PathVariable Long id, @ModelAttribute Rental rental) {
        rentalService.updateRental(id, rental);
        return "redirect:/rentals";
    }

    @GetMapping("/delete/{id}")
    public String deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals";
    }
}


