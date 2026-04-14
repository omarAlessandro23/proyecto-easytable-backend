package com.example.easytable.Controllers;

import com.example.easytable.Dtos.UserInteractionDTO;
import com.example.easytable.Entities.UserInteraction;
import com.example.easytable.Serviceinterfaces.IUserInteractionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/UserInteraction")
public class UserInteractionController {

    @Autowired
    private IUserInteractionService uS;

    @GetMapping("/listar")
    public List<UserInteractionDTO> list() {
        return uS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserInteractionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<String> insert(@RequestBody UserInteractionDTO dto) {
        ModelMapper m = new ModelMapper();
        UserInteraction u = m.map(dto, UserInteraction.class);
        uS.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Interacción registrada correctamente.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserInteractionDTO dto) {
        UserInteraction ex = uS.listId(id);
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una interacción con ese ID.");
        }

        ModelMapper m = new ModelMapper();
        UserInteraction u = m.map(dto, UserInteraction.class);
        u.setInteractionId(id);
        uS.update(u);

        return ResponseEntity.ok("Interacción actualizada correctamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        UserInteraction userInteraction = uS.listId(id);
        if (userInteraction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una interacción con el ID: " + id);
        }
        uS.delete(id);
        return ResponseEntity.ok("Interacción eliminada correctamente.");
    }

    @GetMapping("/usuario/{userId}")
    public List<UserInteractionDTO> findByUserId(@PathVariable int userId) {
        return uS.findByUserId(userId).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserInteractionDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/restaurante/{restaurantId}")
    public List<UserInteractionDTO> findByRestaurantId(@PathVariable int restaurantId) {
        return uS.findByRestaurantId(restaurantId).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserInteractionDTO.class);
        }).collect(Collectors.toList());
    }
}
