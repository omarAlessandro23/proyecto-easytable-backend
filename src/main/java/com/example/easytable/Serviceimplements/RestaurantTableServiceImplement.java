package com.example.easytable.Serviceimplements;


import com.example.easytable.Entities.RestaurantTable;
import com.example.easytable.Repositories.IRestaurantRepository;
import com.example.easytable.Repositories.IRestaurantTableRepository;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IRestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableServiceImplement implements IRestaurantTableService {

    @Autowired
    private IRestaurantTableRepository rtR;

    @Override
    public List<RestaurantTable> list(){
        return rtR.findAll();
    }

    @Override
    public void insert(RestaurantTable restaurantTable){
        rtR.save(restaurantTable);
    }

    @Override
    public void delete(int id){
        rtR.deleteById(id);
    }

    @Override
    public RestaurantTable listId(int id) {
        return rtR.findById(id).orElse(new RestaurantTable());
    }

    @Override
    public List<Object[]> totalCapacityByRestaurant() {
        return rtR.totalCapacityByRestaurant();
    }

}
