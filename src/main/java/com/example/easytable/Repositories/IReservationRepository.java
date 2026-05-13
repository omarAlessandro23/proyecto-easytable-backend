package com.example.easytable.Repositories;

import com.example.easytable.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.usuario.idUsuario = :userId")
    List<Reservation> findByUserId(@Param("userId") int userId);

    // USAMOS restaurantid (todo minúsculas) porque así está en tu clase Restaurant
    @Query("SELECT r FROM Reservation r WHERE r.restaurant.restaurantid = :restaurantId")
    List<Reservation> findByRestaurantId(@Param("restaurantId") int restaurantId);


    List<Reservation> findByStatus(String status);
}
