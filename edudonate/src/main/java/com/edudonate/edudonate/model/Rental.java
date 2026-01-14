package com.edudonate.edudonate.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;   // Item being rented
    private String description;
    private String rentedBy;   // Who is giving it
    private String rentedTo;   // Who is taking it
    private LocalDate startDate;   // Rental start date
    private LocalDate endDate;     // Rental return date
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private RentalStatus status = RentalStatus.AVAILABLE; // New field with default

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRentedBy() { return rentedBy; }
    public void setRentedBy(String rentedBy) { this.rentedBy = rentedBy; }

    public String getRentedTo() { return rentedTo; }
    public void setRentedTo(String rentedTo) { this.rentedTo = rentedTo; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public RentalStatus getStatus() { return status; }
    public void setStatus(RentalStatus status) { this.status = status; }
}




