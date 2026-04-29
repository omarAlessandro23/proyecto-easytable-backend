package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Favoritos;

import java.util.List;

public interface IFavoritoService {
    public List<Favoritos> list();
    public void insert(Favoritos fav);
    public void delete(int id);
    public void update(Favoritos fav);
    public Favoritos listId(int id);
}
