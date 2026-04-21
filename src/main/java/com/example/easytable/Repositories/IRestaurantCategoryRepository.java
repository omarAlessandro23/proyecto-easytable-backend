package com.example.easytable.Repositories;

import com.example.easytable.Entities.RestaurantCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantCategoryRepository extends JpaRepository<RestaurantCategoryMap, Integer> {
    @Query("SELECT rc.category.NombreCategoria, rc.restaurant.name " +
            "FROM RestaurantCategoryMap rc " +
            "ORDER BY rc.category.NombreCategoria")
    List<Object[]> restaurantxcategoria();

}
