package com.example.easytable.Dtos;

public class RestaurantCategoryQuery1DTO {
    private String NombreCategoria;
    private int contador;

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        NombreCategoria = nombreCategoria;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
