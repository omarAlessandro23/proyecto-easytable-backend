package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Reservation;

import java.util.List;

public interface ICategoryService {
    public List<Category> list();
    public void insert(Category category);
    public void delete(int id);
    public void update(Category category);
    public Category listId(int id);
}
