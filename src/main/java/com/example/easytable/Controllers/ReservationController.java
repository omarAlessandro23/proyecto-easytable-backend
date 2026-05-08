package com.example.easytable.Controllers;

import com.example.easytable.Dtos.ReservationDTO;
import com.example.easytable.Entities.Reservation;
import com.example.easytable.Serviceinterfaces.IReservationService;
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

    @GetMapping("/listar")
    public List<ReservationDTO> list() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReservationDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/registrar")
    public ResponseEntity<String> insert(@RequestBody ReservationDTO dto) {
        ModelMapper m = new ModelMapper();
        Reservation r = m.map(dto, Reservation.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reserva registrada correctamente.");
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ReservationDTO dto) {
        Reservation ex = rS.listId(id);
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una reserva con ese ID.");
        }

        ModelMapper m = new ModelMapper();
        Reservation r = m.map(dto, Reservation.class);
        r.setReservationId(id);
        rS.update(r);

        return ResponseEntity.ok("Reserva actualizada correctamente");
    }
    @PreAuthorize("hasRole('USER')")
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
    @PreAuthorize("hasRole('USER')")

    @GetMapping("/buscar-estado/{status}")
    public List<ReservationDTO> buscarPorEstado(@PathVariable String status) {
        return rS.findByStatus(status).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReservationDTO.class);
        }).toList();
    }

}

