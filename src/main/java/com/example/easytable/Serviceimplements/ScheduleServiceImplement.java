package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Schedule;
import com.example.easytable.Repositories.ISchedulesRepository;
import com.example.easytable.Serviceinterfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImplement implements IScheduleService {

    @Autowired
    private ISchedulesRepository sR;

    @Override
    public List<Schedule> list(){
        return sR.findAll();
    }

    @Override
    public void insert(Schedule schedule){
        sR.save(schedule);
    }

    @Override
    public void delete(int id){
        sR.deleteById(id);
    }

    @Override
    public Schedule listId(int id){
        return sR.findById(id).orElse(new Schedule());
    }

    @Override
    public List<Object[]> schedulesByRestaurant() {
         return sR.schedulesByRestaurant();
    }


}
