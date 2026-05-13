package com.example.easytable.Repositories;

import com.example.easytable.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.ratingAvg >= :minRating ORDER BY r.ratingAvg DESC")
    List<Restaurant> findTopRatedRestaurants(@Param("minRating") Double minRating);

    @Query(value = "SELECT * FROM Restaurant r " +
            "WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(r.latitude)) * " +
            "cos(radians(r.longitude) - radians(:lng)) + sin(radians(:lat)) * " +
            "sin(radians(r.latitude)))) < :distance",
            nativeQuery = true)
    List<Restaurant> findNearbyRestaurants(@Param("lat") Double lat,
                                           @Param("lng") Double lng,
                                           @Param("distance") Double distance);


}
