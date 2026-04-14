package com.example.easytable.Repositories;


import com.example.easytable.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByReviewId(int userId);
    List<Review> findByUserId(int restaurantId);
}
