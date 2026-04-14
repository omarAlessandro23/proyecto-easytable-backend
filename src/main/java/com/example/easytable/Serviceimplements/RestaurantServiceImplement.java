package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Repositories.IRestaurantRepository;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantServiceImplement implements IRestaurantService {
    @Autowired
    private IRestaurantRepository Rr;
    @Override
    public List<Restaurant> list() {
        return Rr.findAll();
    }
    @Override
    public void insert(Restaurant usuario) {
        Rr.save(usuario);
    }

    @Override
    public void delete(int id) {
        Rr.deleteById(id);
    }

    @Override
    public void update(Restaurant usuario) {
        Rr.save(usuario);
    }

    @Override
    public Restaurant listId(int id) {
        return Rr.findById(id).get();
    }
}
