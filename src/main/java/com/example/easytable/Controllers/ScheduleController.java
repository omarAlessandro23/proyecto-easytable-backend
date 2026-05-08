package com.example.easytable.Controllers;

import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Schedule;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private IScheduleService sS;

    @Autowired
    private IRestaurantService rS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @GetMapping
    public List<Schedule> listar(){
        return sS.list();
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @PostMapping
    public void insertar(@RequestBody Schedule schedule){
        if(schedule.getDayOfWeek()<0 || schedule.getDayOfWeek()>6){
            throw new RuntimeException(("El dia debe ser entre 0 y 6"));
        }
        if (schedule.getOpenTime().isAfter(schedule.getCloseTime())) {
            throw new RuntimeException("La hora de apertura no puede ser mayor a la hora de cierre");
        }

        Restaurant restaurant= rS.listId(schedule.getRestaurant().getId());
        schedule.setRestaurant(restaurant);

        sS.insert(schedule);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id")int id){
        sS.delete(id);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @GetMapping("/{id}")
    public Schedule listarId(@PathVariable("id")int id){
        return sS.listId(id);
    }
}

