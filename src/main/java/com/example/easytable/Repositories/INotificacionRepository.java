package com.example.easytable.Repositories;

import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByFecha(LocalDate fecha);
}
