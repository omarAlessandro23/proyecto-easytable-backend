package com.example.easytable.Dtos;

public class FavoriteDTO {
    private int favoriteid;
    private int usuarioid;
    private int restaurantid;
    private String comentario;

    public int getFavoriteid() {
        return favoriteid;
    }

    public void setFavoriteid(int favoriteid) {
        this.favoriteid = favoriteid;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
