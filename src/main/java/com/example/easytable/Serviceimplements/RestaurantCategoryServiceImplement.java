package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.RestaurantCategoryMap;
import com.example.easytable.Repositories.IRestaurantCategoryRepository;
import com.example.easytable.Serviceinterfaces.IFavoritoService;
import com.example.easytable.Serviceinterfaces.IRestaurantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantCategoryServiceImplement implements IRestaurantCategoryService {
    @Autowired
    private IRestaurantCategoryRepository rc;
    @Override
    public List<RestaurantCategoryMap> list() {
        return rc.findAll();
    }

    @Override
    public void insert(RestaurantCategoryMap rcat) {
        rc.save(rcat);
    }

    @Override
    public void delete(int id) {
        rc.deleteById(id);
    }

    @Override
    public void update(RestaurantCategoryMap rcat) {
        rc.save(rcat);
    }

    @Override
    public RestaurantCategoryMap listId(int id) {
        return rc.findById(id).get();
    }

    @Override
    public List<Object[]> restaurantxcategoria() {
        return rc.restaurantxcategoria();
    }


}
