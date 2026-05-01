package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Review;

import java.util.List;

public interface IReviewService {
    public List<Review> list();
    public void insert(Review review);
    public void delete(int id);
    public void update(Review review);
    public Review listId(int id);

    public List<Review> findByUserId(int userId);
    public List<Review> findByRestaurantId(int restaurantId);
    public List<Object[]> promedioRatingPorRestaurante();
}
