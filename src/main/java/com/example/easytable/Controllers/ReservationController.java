package com.example.easytable.Controllers;

import com.example.easytable.Dtos.ReservationDTO;
import com.example.easytable.Dtos.ReservationListDTO;
import com.example.easytable.Entities.*;
import com.example.easytable.Serviceinterfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Reservacion")
public class ReservationController {

    @Autowired
    private IReservationService rS;
    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IRestaurantTableService tableService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IScheduleService scheduleService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
    @GetMapping("/listar")
    public List<ReservationListDTO> list() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReservationListDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
    @PostMapping("/registrar")
    public ResponseEntity<String> insert(@RequestBody ReservationDTO dto) {

        // VALIDAR PERSONAS
        if (dto.getNumberPeople() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El número de personas debe ser mayor a 0.");
        }

        // BUSCAR USUARIO
        Usuario usuario = usuarioService.listId(dto.getIdUsuario());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Usuario no encontrado.");
        }

        // BUSCAR RESTAURANTE
        Restaurant restaurant = restaurantService.listId(dto.getRestaurantid());

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Restaurante no encontrado.");
        }
        // BUSCAR HORARIO
        Schedule schedule = scheduleService.listId(dto.getScheduleId());

        if (schedule == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: schedule no encontrado.");
        }

        // BUSCAR MESA
        RestaurantTable table = tableService.listId(dto.getTableId());

        if (table == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Mesa no encontrada.");
        }

        // CREAR RESERVA
        Reservation r = new Reservation();

        r.setUsuario(usuario);
        r.setRestaurant(restaurant);
        r.setRestaurantTable(table);
        r.setSchedule(schedule);

        r.setReservationDate(dto.getReservationDate());
        r.setStatus(dto.getStatus());
        r.setNumberPeople(dto.getNumberPeople());

        // ENVIAR AL SERVICE
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reserva registrada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ReservationDTO dto) {
        // 1. Buscar la reserva existente
        Reservation ex = rS.listId(id);
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una reserva con el ID: " + id);
        }

        // 2. Validación: Número de personas
        if (dto.getNumberPeople() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El número de personas debe ser mayor a 0.");
        }

        // 3. Validación: Restaurante (si el DTO trae un restaurantId)
        if (restaurantService.listId(dto.getRestaurantid()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se puede actualizar. El Restaurante ID " + dto.getReservationId() + " no existe.");
        }

        // 4. Validación: Mesa
        if (tableService.listId(dto.getTableId()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se puede actualizar. el tableID " + dto.getTableId() + " no existe.");
        }

        // 5. Mapeo seguro para no perder datos
        ModelMapper m = new ModelMapper();
        m.getConfiguration().setSkipNullEnabled(true);
        m.map(dto, ex);
        ex.setReservationId(id);

        rS.update(ex);

        return ResponseEntity.ok("Reserva actualizada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Reservation reservation = rS.listId(id);
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una reserva con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Reserva eliminada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")

    @GetMapping("/buscar-estado/{status}")


    public List<ReservationDTO> buscarPorEstado(@PathVariable String status) {
        return rS.findByStatus(status).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReservationDTO.class);
        }).toList();
    }

}

