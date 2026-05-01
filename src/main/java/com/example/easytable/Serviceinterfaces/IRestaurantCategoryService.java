package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.RestaurantCategoryMap;

import java.util.List;

public interface IRestaurantCategoryService {
    public List<RestaurantCategoryMap> list();
    public void insert(RestaurantCategoryMap rcat);
    public void delete(int id);
    public void update(RestaurantCategoryMap rcat);
    public RestaurantCategoryMap listId(int id);
    List<Object[]> contarRestaurantesPorCategoria();
}
