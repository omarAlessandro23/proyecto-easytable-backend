package com.example.easytable.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int scheduleId;
    @Column(name = "day_of_week", nullable = false)
    private int dayOfWeek;

    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", example = "09:00", description = "Hora de apertura en formato HH:mm")
    @Column(name = "open_time", nullable = false)
    private LocalTime openTime;
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", example = "22:00", description = "Hora de cierre en formato HH:mm")
    @Column(name = "close_time", nullable = false)
    private LocalTime closeTime;

    public Schedule() {
    }

    public Schedule(int scheduleId, int dayOfWeek, LocalTime openTime, LocalTime closeTime) {
        this.scheduleId = scheduleId;
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
}

