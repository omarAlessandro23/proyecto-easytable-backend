package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Reservation;
import com.example.easytable.Repositories.IReservationRepository;
import com.example.easytable.Serviceinterfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImplement implements IReservationService {
    @Autowired
    private IReservationRepository rR;

    @Override
    public List<Reservation> list() {

        return rR.findAll();

    }
    @Override
    public void insert(Reservation reservation) {

        if(reservation.getNumberPeople() <= 0) {
            throw new RuntimeException("La cantidad de personas debe ser mayor a 0");

        }
        if (reservation.getStatus() == null || reservation.getStatus().isEmpty()) {
            reservation.setStatus("pending");
    }
        rR.save(reservation);

    }
    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public void update(Reservation reservation) {
        rR.save(reservation);
    }
    @Override
    public Reservation listId(int id) {
        return rR.findById(id).orElse(new Reservation());
    }

    @Override
    public List<Reservation> findByUserId(int userId) {
        return rR.findByUserId(userId);
    }

    @Override
    public List<Reservation> findByRestaurantId(int restaurantId) {
        return rR.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Reservation> findByUser_Id(int userId) {
        return rR.findByUser_Id(userId);
    }
}
