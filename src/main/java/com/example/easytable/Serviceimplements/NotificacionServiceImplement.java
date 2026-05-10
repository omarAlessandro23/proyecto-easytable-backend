package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Repositories.INotificacionRepository;
import com.example.easytable.Serviceinterfaces.INotificacionService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class NotificacionServiceImplement implements INotificacionService {
    @Autowired
    private INotificacionRepository NR;
    @Override
    public List<Notificacion> list() {
        return NR.findAll();
    }

    @Override
    public void insert(Notificacion notificacion) {
        NR.save(notificacion);
    }

    @Override
    public void delete(int id) {
        NR.deleteById(id);
    }

    @Override
    public void update(Notificacion notificacion) {
        NR.save(notificacion);
    }

    @Override
    public Notificacion listId(int id) {
        return NR.findById(id).orElse(null);
    }

    @Override
    public List<Object[]> findByfecha(LocalDate fecha) {
        return NR.findByfecha(fecha);
    }


}