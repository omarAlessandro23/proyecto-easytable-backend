package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.Restaurant;

import java.util.List;

public interface IFavoriteService {
    public List<Favorite> list();
    public void insert(Favorite favorite);
    public void delete(int idUsuario, int idRestaurant);
    public void update(Favorite favorite);
    public Favorite listId(int idUsuario, int idRestaurant);
    public List<Favorite> listByUser(int idUsuario);
    public boolean esFavorito(int userId, int resId);
    public List<Restaurant> listarFavoritosPorUsuario(int userId);
    public List<Restaurant> obtenerSugerencias(int userId);
}
