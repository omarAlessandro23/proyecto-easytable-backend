package com.example.easytable.Controllers;

import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Schedule;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/listar")
    public List<Schedule> listar(){
        return sS.list();
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @PostMapping("/registrar")
    public ResponseEntity<?> insertar(@RequestBody Schedule schedule) {
        // Validaciones manuales
        if (schedule.getDayOfWeek() < 0 || schedule.getDayOfWeek() > 6) {
            return ResponseEntity.badRequest().body("El día debe ser entre 0 (Domingo) y 6 (Sábado)");
        }

        if (schedule.getOpenTime().isAfter(schedule.getCloseTime())) {
            return ResponseEntity.badRequest().body("La hora de apertura no puede ser mayor a la de cierre");
        }

        sS.insert(schedule);
        return ResponseEntity.ok("Horario registrado correctamente");
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

