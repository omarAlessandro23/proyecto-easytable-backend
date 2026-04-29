package com.example.easytable.Repositories;

import com.example.easytable.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(int userId);

    List<Reservation> findByRestaurantId(int restaurantId);

    @Query("SELECT res.name, COUNT(r) " +
            "FROM Reservation r, Restaurant res " +
            "WHERE r.restaurantId = res.id " +
            "GROUP BY res.name " +
            "ORDER BY COUNT(r) DESC")
    List<Object[]> mostReservedRestaurantNames();;

}
