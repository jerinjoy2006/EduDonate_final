package com.edudonate.edudonate.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "exchanges")
public class Exchange {

    @Id
    private String id;

    private String fromUser;
    private String itemOffered;
    private String itemRequested;
    private String acceptedBy;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    public enum Status {
        PENDING, ACCEPTED, COMPLETED
    }

    // Default constructor for JPA
    public Exchange() {}

    // Convenience constructor
    public Exchange(String fromUser, String itemOffered, String itemRequested) {
        this.id = UUID.randomUUID().toString();
        this.fromUser = fromUser;
        this.itemOffered = itemOffered;
        this.itemRequested = itemRequested;
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = Status.PENDING;
    }

    // Getters & Setters (required by Thymeleaf/JPA)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFromUser() { return fromUser; }
    public void setFromUser(String fromUser) { this.fromUser = fromUser; }

    public String getItemOffered() { return itemOffered; }
    public void setItemOffered(String itemOffered) { this.itemOffered = itemOffered; }

    public String getItemRequested() { return itemRequested; }
    public void setItemRequested(String itemRequested) { this.itemRequested = itemRequested; }

    public String getAcceptedBy() { return acceptedBy; }
    public void setAcceptedBy(String acceptedBy) { this.acceptedBy = acceptedBy; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
