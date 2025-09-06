package com.edudonate.edudonate.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fromUser;
    private String toUser;
    private String itemOffered;
    private String itemRequested;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate createdAt;

    public enum Status { PENDING, ACCEPTED, REJECTED, COMPLETED }

    public Exchange() {}

    public Exchange(String fromUser, String toUser, String itemOffered, String itemRequested) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.itemOffered = itemOffered;
        this.itemRequested = itemRequested;
        this.status = Status.PENDING;
        this.createdAt = LocalDate.now();
    }

    // Getters & setters
    public String getId() { return id; }
    public String getFromUser() { return fromUser; }
    public void setFromUser(String fromUser) { this.fromUser = fromUser; }
    public String getToUser() { return toUser; }
    public void setToUser(String toUser) { this.toUser = toUser; }
    public String getItemOffered() { return itemOffered; }
    public void setItemOffered(String itemOffered) { this.itemOffered = itemOffered; }
    public String getItemRequested() { return itemRequested; }
    public void setItemRequested(String itemRequested) { this.itemRequested = itemRequested; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}

