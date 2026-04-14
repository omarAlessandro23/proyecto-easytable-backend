package com.example.easytable.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @Column(name = "nameCategory",nullable = false,length = 50)
    private String nameCategory;

    @Column(name = "description",length = 255)
    private String description;
}
