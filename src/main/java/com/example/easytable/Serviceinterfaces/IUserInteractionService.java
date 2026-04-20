package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Favoritos;
import com.example.easytable.Entities.UserInteraction;

import java.util.List;

public interface IUserInteractionService {
    public List<UserInteraction> list();
    public void insert(UserInteraction useri);
    public void delete(int id);
    public void update(UserInteraction useri);
    public UserInteraction listId(int id);
}
