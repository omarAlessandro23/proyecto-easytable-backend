package com.example.easytable.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @Column(name="NombreCategoria",length=25,nullable=false)
    private String nombreCategoria;

    public Category() {
    }

    public Category(int idCategory, String nombreCategoria) {
        this.idCategory = idCategory;
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
