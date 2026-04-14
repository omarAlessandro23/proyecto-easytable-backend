package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
   public List<Restaurant> list();

    public  void insert(Restaurant usuario);

    public void delete(int id);

    public void update(Restaurant usuario);

    public Restaurant listId(int id);
}
