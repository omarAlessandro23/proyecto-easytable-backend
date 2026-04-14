package com.example.easytable.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="user_interactions")

public class UserInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="interaction_id")
    private int interactionId;

    @Column(name ="user_id", nullable = false)
    private int userId;

    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserInteraction(int interactionId, int userId, int restaurantId, LocalDateTime createdAt) {
        this.interactionId = interactionId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.createdAt = createdAt;
    }

    public int getInteractionId() {
        return interactionId;

    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
