package com.example.easytable.Repositories;


import com.example.easytable.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByReviewId(int userId);
    List<Review> findByUserId(int restaurantId);
    @Query(value = "SELECT r.name, AVG(rv.rating) AS promedio " +
            "FROM reviews rv " +
            "JOIN restaurant r ON rv.restaurant_id = r.restaurant_id " +
            "GROUP BY r.name " +
            "ORDER BY promedio DESC",
            nativeQuery = true)
    List<Object[]> promedioRatingPorRestaurante();
}
