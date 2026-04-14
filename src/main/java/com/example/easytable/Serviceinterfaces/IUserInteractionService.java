package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.UserInteraction;

import java.util.List;

public interface IUserInteractionService {
    public List<UserInteraction> list();
    public void insert(UserInteraction userInteraction);
    public void delete(int id);
    public void update(UserInteraction userInteraction);
    public UserInteraction listId(int id);

    public List<UserInteraction> findByUserId(int userId);
    public List<UserInteraction> findByRestaurantId(int restaurantId);
}
