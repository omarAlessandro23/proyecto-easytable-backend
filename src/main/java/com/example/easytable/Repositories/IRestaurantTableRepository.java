package com.example.easytable.Repositories;


import com.example.easytable.Entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantTableRepository  extends JpaRepository<RestaurantTable, Integer> {
}
