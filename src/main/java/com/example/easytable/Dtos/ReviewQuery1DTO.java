package com.example.easytable.Dtos;

public class ReviewQuery1DTO {
    private String Restaurante;
    private double promedio;

    public String getRestaurante() {
        return Restaurante;
    }

    public void setRestaurante(String restaurante) {
        Restaurante = restaurante;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
