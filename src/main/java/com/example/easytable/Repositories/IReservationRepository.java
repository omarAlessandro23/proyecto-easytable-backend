package com.example.easytable.Repositories;

import com.example.easytable.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(int userId);

    List<Reservation> findByRestaurantId(int restaurantId);

    List<Reservation> findByUser_Id(int userId);
}
