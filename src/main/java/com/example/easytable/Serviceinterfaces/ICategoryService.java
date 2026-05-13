package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> list();
    public void insert(Category category);
    public void delete(int id);
    public void update(Category category);
    public Category listId(int id);
    public List<Category> findByName(String name);
    public List<Category> findByNameLike(String name);
    public List<Object[]> obtenerRankingPopularidad();
    public List<Category> listarCategoriasHuerfanas();
    List<Object[]> categoriasSinRestaurantes();

}
