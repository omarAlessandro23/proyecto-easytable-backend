package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.Restaurant;

import java.util.List;

public interface IFavoriteService {
    public List<Favorite> list();
    public void insert(Favorite favorite);
    public void delete(int id);

    public void update(Favorite favorite);
    public List<Object[]> countFavoritesByRestaurant();
    public Favorite listId(int id);
    public List<Favorite> listByUser(int idUsuario);

}
