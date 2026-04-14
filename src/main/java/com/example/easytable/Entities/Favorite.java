package com.example.easytable.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Favorite")
@IdClass(FavoriteId.class)
public class Favorite {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Favorite() {
    }

    public Favorite(Usuario usuario, Restaurant restaurant) {
        this.usuario = usuario;
        this.restaurant = restaurant;
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
