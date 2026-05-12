package com.example.easytable.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idNotificacion;

    @Column(name="mensaje",length = 100,nullable = false)
    private String mensaje;

    @Column(name="leido",nullable = false)
    private Boolean leido;

    @Column(name="fecha",nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public Notificacion() {
    }

    public Notificacion(int idNotificacion, String mensaje, Boolean leido, LocalDate fecha, Usuario usuario) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.leido = leido;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
