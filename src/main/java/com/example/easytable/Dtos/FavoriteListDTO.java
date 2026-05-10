package com.example.easytable.Dtos;

import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Usuario;

public class FavoriteListDTO {
    private int favoriteid;
    private Usuario usuario;
    private Restaurant restaurant;
    private String comentario;

    public int getFavoriteid() {
        return favoriteid;
    }

    public void setFavoriteid(int favoriteid) {
        this.favoriteid = favoriteid;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
