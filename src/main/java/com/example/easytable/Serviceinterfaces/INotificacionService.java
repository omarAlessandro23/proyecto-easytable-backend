package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Usuario;

import java.util.List;

public interface INotificacionService {
    public List<Notificacion> list();
    public void insert(Notificacion notificacion);
    public void delete(int id);
    public void update(Notificacion notificacion);
    public Notificacion listId(int id);
}
