package com.example.easytable.Controllers;

import com.example.easytable.Dtos.ReservationDTO;
import com.example.easytable.Entities.Reservation;
import com.example.easytable.Serviceinterfaces.IReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Reservation")
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

    @PostMapping("/register")
    public ResponseEntity<String> insert(@RequestBody ReservationDTO dto) {
        ModelMapper m = new ModelMapper();
        Reservation r = m.map(dto, Reservation.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Reserva registrada correctamente.");
    }

    @PutMapping("/update/{id}")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Reservation reservation = rS.listId(id);
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una reserva con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Reserva eliminada correctamente.");
    }

        @GetMapping("/reservas-por-usuario")
    public ResponseEntity<List<ReservationDTO>> getReservaxUsuario(@RequestParam Integer userId) {

        List<Reservation> reservas = rS.findByUserId(userId);

        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }

        List<ReservationDTO> dtoList = reservas.stream().map(r -> {
            ReservationDTO dto = new ReservationDTO();

            dto.setReservationId(r.getReservationId()); // ajusta si tu campo se llama distinto
            dto.setUserId(r.getUserId());
            dto.setRestaurantId(r.getRestaurantId());
            dto.setTableId(r.getTableId());
            dto.setReservationDate(r.getReservationDate().atStartOfDay());
            dto.setStatus(r.getStatus());
            dto.setNumberPeople(r.getNumberPeople());

            return dto;
        }).toList();

        return ResponseEntity.ok(dtoList);
    }

}

