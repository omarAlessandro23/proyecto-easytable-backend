package com.example.easytable.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="userinteraction")
public class UserInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserInteraction;

    @Column(name="fechaCreacion",nullable = false)
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name="idRestaurant")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public UserInteraction() {
    }

    public UserInteraction(int idUserInteraction, LocalDate fechaCreacion, Restaurant restaurant, Usuario usuario) {
        this.idUserInteraction = idUserInteraction;
        this.fechaCreacion = fechaCreacion;
        this.restaurant = restaurant;
        this.usuario = usuario;
    }

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
