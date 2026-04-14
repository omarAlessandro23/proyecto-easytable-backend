package com.example.easytable.Entities;

import java.io.Serializable;
import java.util.Objects;

public class FavoriteId implements Serializable {
    private int usuario;
    private int restaurant;

    public FavoriteId() {}

    public FavoriteId(int usuario, int restaurant) {
        this.usuario = usuario;
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return usuario == that.usuario && restaurant == that.restaurant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, restaurant);
    }
}
