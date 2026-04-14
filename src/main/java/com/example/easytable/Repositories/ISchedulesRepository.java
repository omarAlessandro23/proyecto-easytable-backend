package com.example.easytable.Repositories;

import com.example.easytable.Entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISchedulesRepository extends JpaRepository<Schedule, Integer> {
}
