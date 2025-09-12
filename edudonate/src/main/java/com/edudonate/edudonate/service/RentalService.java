package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.Rental;
import com.edudonate.edudonate.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    public Rental updateRental(Long id, Rental updatedRental) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    rental.setItemName(updatedRental.getItemName());
                    rental.setDescription(updatedRental.getDescription());
                    rental.setRentedBy(updatedRental.getRentedBy());
                    rental.setRentedTo(updatedRental.getRentedTo());
                    rental.setStartDate(updatedRental.getStartDate());
                    rental.setEndDate(updatedRental.getEndDate());
                    rental.setActive(updatedRental.isActive());
                    return rentalRepository.save(rental);
                })
                .orElseThrow(() -> new RuntimeException("Rental not found with id " + id));
    }
}


