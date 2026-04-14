package com.example.easytable.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "reservations")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name ="user_id", nullable=false)
    private int userId;

    @Column(name ="restaurant_id", nullable=false)
    private int restaurantId;

    @Column(name ="table_id")
    private int tableId;

    @Column(name ="reservation_date", nullable=false)
    private LocalDate reservationDate;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name ="number_people", nullable=false)
    private int numberPeople;

    public Reservation(int reservationId, int userId, int restaurantId, int tableId, LocalDate reservationDate, String status, int numberPeople) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.tableId = tableId;
        this.reservationDate = reservationDate;
        this.status = status;
        this.numberPeople = numberPeople;
    }

    public Reservation() {

    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }
}
