package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.UserInteraction;
import com.example.easytable.Repositories.IUserInteractionRepository;
import com.example.easytable.Serviceinterfaces.IUserInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInteractionServiceImplement implements IUserInteractionService {
    @Autowired
    private IUserInteractionRepository ui;
    @Override
    public List<UserInteraction> list() {
        return ui.findAll();
    }

    @Override
    public void insert(UserInteraction useri) {
        ui.save(useri);
    }

    @Override
    public void delete(int id) {
        ui.deleteById(id);
    }

    @Override
    public void update(UserInteraction useri) {
        ui.save(useri);
    }

    @Override
    public UserInteraction listId(int id) {
        return ui.findById(id).get();
    }
}
