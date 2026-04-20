package com.example.easytable.Repositories;

import com.example.easytable.Entities.RestaurantCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantCategoryRepository extends JpaRepository<RestaurantCategoryMap, Integer> {
}
