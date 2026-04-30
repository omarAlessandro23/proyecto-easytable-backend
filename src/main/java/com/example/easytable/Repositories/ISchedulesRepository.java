package com.example.easytable.Repositories;

import com.example.easytable.Entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISchedulesRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = "SELECT r.name, COUNT(s.schedule_id) AS total_schedules " +
            "FROM schedules s " +
            "INNER JOIN restaurant r ON s.restaurant_id = r.restaurant_id " +
            "GROUP BY r.name " +
            "ORDER BY total_schedules DESC",
            nativeQuery = true)
    List<Object[]> schedulesByRestaurant();
}
