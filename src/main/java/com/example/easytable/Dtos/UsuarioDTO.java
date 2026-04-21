package com.example.easytable.Dtos;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO {
    private int idUsuario;
    private String username;
    private String correo;
    private String contrasenia;
    private LocalDate horacreacion;
    private String nombrecompleto;
    private int numcelular;
    private String ciudad;
    private Double longitud;
    private Double latitud;
    private List<RoleDTO> roles;

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
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
