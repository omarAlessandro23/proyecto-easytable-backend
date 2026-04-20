package com.example.easytable.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavorito;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id")
    private Restaurant restaurant;

    public Favoritos() {
    }

    public Favoritos(int idFavorito, Usuario usuario, Restaurant restaurant) {
        this.idFavorito = idFavorito;
        this.usuario = usuario;
        this.restaurant = restaurant;
    }

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
