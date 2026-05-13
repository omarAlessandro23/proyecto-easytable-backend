package com.example.easytable.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="restaurant_category_map")
public class RestaurantCategoryMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRestaurantCategoryMap;

    @ManyToOne
    @JoinColumn(name="id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="idCategory")
    private Category category;

    public RestaurantCategoryMap() {
    }

    public RestaurantCategoryMap(int idRestaurantCategoryMap, Restaurant restaurant, Category category) {
        this.idRestaurantCategoryMap = idRestaurantCategoryMap;
        this.restaurant = restaurant;
        this.category = category;
    }

    public int getIdRestaurantCategoryMap() {
        return idRestaurantCategoryMap;
    }

    public void setIdRestaurantCategoryMap(int idRestaurantCategoryMap) {
        this.idRestaurantCategoryMap = idRestaurantCategoryMap;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
