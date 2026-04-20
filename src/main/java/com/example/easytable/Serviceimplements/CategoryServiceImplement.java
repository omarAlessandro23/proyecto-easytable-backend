package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Category;
import com.example.easytable.Repositories.ICategoryRepository;
import com.example.easytable.Serviceinterfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplement implements ICategoryService {
    @Autowired
    private ICategoryRepository cr;
    @Override
    public List<Category> list() {
        return cr.findAll();
    }

    @Override
    public void insert(Category category) {
        cr.save(category);
    }

    @Override
    public void delete(int id) {
        cr.deleteById(id);
    }

    @Override
    public void update(Category category) {
        cr.save(category);
    }

    @Override
    public Category listId(int id) {
        return cr.findById(id).get();
    }
}
