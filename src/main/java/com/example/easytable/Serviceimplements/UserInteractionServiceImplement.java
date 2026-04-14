package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.UserInteraction;
import com.example.easytable.Repositories.IUserInteractionRepository;
import com.example.easytable.Serviceinterfaces.IUserInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserInteractionServiceImplement implements IUserInteractionService {

    @Autowired
    private IUserInteractionRepository uR;

    @Override
    public List<UserInteraction> list() {
        return uR.findAll();
    }
    @Override
    public void insert(UserInteraction userInteraction) {
        if (userInteraction.getCreatedAt() == null) {
            userInteraction.setCreatedAt(LocalDateTime.now());
        }
        uR.save(userInteraction);
    }
    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }
    @Override
    public void update(UserInteraction userInteraction) {
        uR.save(userInteraction);
    }
    @Override
    public UserInteraction listId(int id) {
        return uR.findById(id).orElse(null);
    }
    @Override
    public List<UserInteraction> findByUserId(int userId) {
        return uR.findByUserId(userId);
    }
    @Override
    public List<UserInteraction> findByRestaurantId(int restaurantId) {
        return uR.findByRestaurantId(restaurantId);
    }
}
