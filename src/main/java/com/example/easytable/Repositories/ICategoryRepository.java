package com.example.easytable.Repositories;

import com.example.easytable.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameCategory(String name);
    List<Category> findByNameCategoryContainingIgnoreCase(String name);
    @Query(value = "SELECT c.id_category, c.name_category, COUNT(f.id_restaurant) as total_favs " +
            "FROM category c " +
            "JOIN restaurant r ON c.id_category = r.id_category " +
            "JOIN favorite f ON r.id_restaurant = f.id_restaurant " +
            "GROUP BY c.id_category, c.name_category " +
            "ORDER BY total_favs DESC", nativeQuery = true)
    List<Object[]> findMostPopularCategoriesNative();
    @Query(value = "SELECT * FROM category c " +
            "WHERE NOT EXISTS (" +
            "    SELECT 1 FROM restaurant r " +
            "    WHERE r.id_category = c.id_category" +
            ")", nativeQuery = true)
    List<Category> findEmptyCategoriesNative();
}
