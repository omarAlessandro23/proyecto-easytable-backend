package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Usuario;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface INotificacionService {
    public List<Notificacion> list();
    public void insert(Notificacion notificacion);
    public void delete(int id);
    public void update(Notificacion notificacion);
    public Notificacion listId(int id);
    List<Object[]> findByfecha(LocalDate fecha);
}