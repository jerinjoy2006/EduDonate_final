package com.edudonate.edudonate.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Exchange {
    @Id
    private String id;

    private String fromUser;       // User who created the exchange
    private String itemOffered;    // Item being offered
    private String itemRequested;  // Item being requested

    private String acceptedBy;     // User who accepts the exchange (initially null)

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate createdAt;

    public enum Status {
        PENDING, ACCEPTED, COMPLETED
    }

    // Default constructor (required by JPA)
    public Exchange() {}

    // Constructor for creating a new exchange
    public Exchange(String fromUser, String itemOffered, String itemRequested) {
        this.id = UUID.randomUUID().toString();
        this.fromUser = fromUser;
        this.itemOffered = itemOffered;
        this.itemRequested = itemRequested;
        this.status = Status.PENDING;
        this.createdAt = LocalDate.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getItemOffered() {
        return itemOffered;
    }

    public void setItemOffered(String itemOffered) {
        this.itemOffered = itemOffered;
    }

    public String getItemRequested() {
        return itemRequested;
    }

    public void setItemRequested(String itemRequested) {
        this.itemRequested = itemRequested;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}


