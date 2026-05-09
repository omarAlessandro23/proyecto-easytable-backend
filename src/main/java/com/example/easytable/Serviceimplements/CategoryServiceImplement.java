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
    private ICategoryRepository CR;
    @Override
    public List<Category> list() { return CR.findAll(); }

    @Override
    public void insert(Category category) { CR.save(category); }

    @Override
    public void delete(int id) { CR.deleteById(id); }

    @Override
    public void update(Category category) { CR.save(category); }

    @Override
    public Category listId(int id) { return CR.findById(id).orElse(null); }

    @Override
    public List<Category> findByName(String name) {
        return CR.findByNombreCategoria(name);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return CR.findByNombreCategoria(name);
    }

    @Override
    public List<Object[]> obtenerRankingPopularidad() {
        return CR.findMostPopularCategoriesNative();
    }

    @Override
    public List<Category> listarCategoriasHuerfanas() {
        return CR.findEmptyCategoriesNative();
    }
}
