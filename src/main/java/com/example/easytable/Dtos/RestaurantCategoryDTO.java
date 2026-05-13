package com.example.easytable.Dtos;

import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Restaurant;

public class RestaurantCategoryDTO {
    private int idRestaurantCategoryMap;
    private Restaurant restaurant;
    private Category category;

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
