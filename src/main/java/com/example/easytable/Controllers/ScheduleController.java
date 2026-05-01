package com.example.easytable.Controllers;

import com.example.easytable.Dtos.ScheduleDTO;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Schedule;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private IScheduleService sS;

    @Autowired
    private IRestaurantService rS;

    @GetMapping("/listar")
    public List<Schedule> listar(){
        return sS.list();
    }

    @PostMapping("/registrar")
    public void insertar(@RequestBody ScheduleDTO dto) {
        if (dto.getDayOfWeek() < 0 || dto.getDayOfWeek() > 6) {
            throw new RuntimeException("El día debe estar entre 0 y 6");
        }

        if (dto.getOpenTime().isAfter(dto.getCloseTime())) {
            throw new RuntimeException("La hora de apertura no puede ser mayor a la de cierre");
        }

        Restaurant restaurant = rS.listId(dto.getRestaurantId());

        if (restaurant == null) {
            throw new RuntimeException("Restaurante no encontrado");
        }

        Schedule schedule = new Schedule();
        schedule.setScheduleId(dto.getScheduleId());
        schedule.setDayOfWeek(dto.getDayOfWeek());
        schedule.setOpenTime(dto.getOpenTime());
        schedule.setCloseTime(dto.getCloseTime());
        schedule.setRestaurant(restaurant);

        sS.insert(schedule);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id")int id){
        sS.delete(id);
    }

    @GetMapping("/listar-id/{id}")
    public Schedule listarId(@PathVariable("id")int id){
        return sS.listId(id);
    }
}

