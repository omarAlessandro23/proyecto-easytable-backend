package com.example.easytable.Repositories;

import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Reservation;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
    @Query(value = "SELECT " +
            "n.id_notificacion AS idNotificacion, " +
            "n.fecha AS fecha, " +
            "n.mensaje AS mensaje " +
            "FROM notificacion n " +
            "WHERE DATE(n.fecha) = :fecha",
            nativeQuery = true)
    List<Object[]> findByfecha(@Param("fecha") LocalDate fecha);
}
