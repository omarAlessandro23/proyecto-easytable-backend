package com.example.easytable.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="reviews")

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="review_id")
    private int reviewId;

    @Column(name ="user_id", nullable = false)
    private int userId;

    @Column(name ="restaurant_id", nullable = false)
    private int restaurantId;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name ="created_at")
    private LocalDateTime createdAt; /*fecha hora de la reseña */

    public Review(int reviewId, int userId, int restaurantId, int rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Review() {

    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
