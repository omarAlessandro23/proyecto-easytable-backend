package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.RestaurantTable;

import java.util.List;

public interface IRestaurantTableService {
    public List<RestaurantTable> list();
    public void insert(RestaurantTable restaurantTable);
    public void delete(int id);
    public RestaurantTable listId(int id);

    List<Object[]> totalCapacityByRestaurant();
}
