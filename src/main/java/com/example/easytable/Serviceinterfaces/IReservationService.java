package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Reservation;

import java.util.Collection;
import java.util.List;

public interface IReservationService {
    public List<Reservation> list();
    public void insert(Reservation reservation);
    public void delete(int id);
    public void update(Reservation reservation);
    public Reservation listId(int id);

    public List<Reservation> findByUserId(int userId);
    public List<Reservation> findByRestaurantId(int restaurantId);

    List<Reservation> findByStatus(String status);

}

