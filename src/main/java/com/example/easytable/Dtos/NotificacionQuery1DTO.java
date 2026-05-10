package com.example.easytable.Dtos;

import com.example.easytable.Entities.Usuario;

import java.time.LocalDate;

public class NotificacionQuery1DTO {
    private String mensaje;
    private Boolean leido;
    private LocalDate fecha;
    private int idUsuario;

    public NotificacionQuery1DTO(String mensaje, Boolean leido, LocalDate fecha, int idUsuario) {
        this.mensaje = mensaje;
        this.leido = leido;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
