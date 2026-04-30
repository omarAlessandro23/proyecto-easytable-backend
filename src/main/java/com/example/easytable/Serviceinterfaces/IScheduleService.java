package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Schedule;

import java.util.List;

public interface IScheduleService {
    public List<Schedule> list();
    public void insert(Schedule schedule);
    public void delete(int id);
    public Schedule listId(int id);

    List<Object[]> schedulesByRestaurant();
}
