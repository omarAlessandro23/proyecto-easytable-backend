package com.example.easytable.Repositories;

import com.example.easytable.Entities.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
}