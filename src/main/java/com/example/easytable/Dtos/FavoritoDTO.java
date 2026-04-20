package com.example.easytable.Dtos;

import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Usuario;

public class FavoritoDTO {
    private int idFavorito;
    private Usuario usuario;
    private Restaurant restaurant;

    public int getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(int idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
