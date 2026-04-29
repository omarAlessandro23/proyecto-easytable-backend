package com.example.easytable.Repositories;

import com.example.easytable.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameCategory(String name);
    List<Category> findByNameCategoryContainingIgnoreCase(String name);
}
