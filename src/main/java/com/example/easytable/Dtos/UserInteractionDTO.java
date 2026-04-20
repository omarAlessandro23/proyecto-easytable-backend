package com.example.easytable.Dtos;

import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Usuario;

import java.time.LocalDate;

public class UserInteractionDTO {
    private int idUserInteraction;
    private LocalDate fechaCreacion;
    private Restaurant restaurant;
    private Usuario usuario;

    public int getIdUserInteraction() {
        return idUserInteraction;
    }

    public void setIdUserInteraction(int idUserInteraction) {
        this.idUserInteraction = idUserInteraction;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
