package com.example.easytable.Repositories;


import com.example.easytable.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRestaurantId(int restaurantId);
    @Query(value = "SELECT r.name, AVG(rv.rating) AS promedio " +
            "FROM reviews rv " +
            "JOIN restaurant r ON rv.restaurant_id = r.restaurant_id " +
            "GROUP BY r.name " +
            "ORDER BY promedio DESC",
            nativeQuery = true)
    List<Object[]> promedioRatingPorRestaurante();


    @Query("SELECT res.name, AVG(rv.rating), COUNT(rv) " +
            "FROM Review rv, Restaurant res " +
            "WHERE rv.restaurantId = res.id " +
            "GROUP BY res.name " +
            "ORDER BY AVG(rv.rating) DESC, COUNT(rv) DESC")
    List<Object[]> recommendedRestaurantsByRating();

    @Query("SELECT u.nombrecompleto, rv.rating, rv.comment\n" +
            "FROM Review rv\n" +
            "JOIN Usuario u\n" +
            "     ON rv.userId = u.idUsuario\n" +
            "WHERE rv.restaurantId = :restaurantId")
    List<Object[]> obtenerReviewsPorRestaurante(@Param("restaurantId") int restaurantId);

}
