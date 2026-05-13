package com.example.easytable.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(name="username",length=50,nullable=false)
    private String username;
    @Column(name="correo",length=100,nullable=false)
    private String correo;
    @Column(name="contrasenia",length=100,nullable=false)
    private String contrasenia;
    @Column(name="horacreacion",nullable=false)
    private LocalDate horacreacion;
    @Column(name="nombrecompleto",length=30,nullable=false)
    private String nombrecompleto;
    @Column(name="numcelular",length=20,nullable=false)
    private int numcelular;
    @Column(name="ciudad",length=50,nullable=false)
    private String ciudad;
    @Column(name="longitud",nullable=false)
    private Double longitud;
    @Column(name="latitud",nullable=false)
    private Double latitud;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String correo, String contrasenia, LocalDate horacreacion, String nombrecompleto, int numcelular, String ciudad, Double longitud, Double latitud) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.horacreacion = horacreacion;
        this.nombrecompleto = nombrecompleto;
        this.numcelular = numcelular;
        this.ciudad = ciudad;
        this.longitud = longitud;
        this.latitud = latitud;
    }
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public LocalDate getHoracreacion() {
        return horacreacion;
    }

    public void setHoracreacion(LocalDate horacreacion) {
        this.horacreacion = horacreacion;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public int getNumcelular() {
        return numcelular;
    }

    public void setNumcelular(int numcelular) {
        this.numcelular = numcelular;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
}
