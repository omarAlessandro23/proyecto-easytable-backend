package com.example.easytable.Repositories;


import com.example.easytable.Entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantTableRepository  extends JpaRepository<RestaurantTable, Integer> {

    @Query(value = "SELECT r.name, SUM(t.capacity) AS total_capacity " +
            "FROM restaurant_tables t " +
            "INNER JOIN restaurant r ON t.restaurant_id = r.restaurant_id " +
            "GROUP BY r.name " +
            "ORDER BY total_capacity DESC",
            nativeQuery = true)
    List<Object[]> totalCapacityByRestaurant();
}
