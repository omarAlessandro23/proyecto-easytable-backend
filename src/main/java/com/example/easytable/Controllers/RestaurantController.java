package com.example.easytable.Controllers;

import com.example.easytable.Dtos.RestaurantDTO;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Restaurant")
public class RestaurantController {

    @Autowired
    private IRestaurantService rS;

    @GetMapping("/listar")
    public List<RestaurantDTO> listar() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RestaurantDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertar(@RequestBody RestaurantDTO dto) {
        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(dto, Restaurant.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante registrado correctamente.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody RestaurantDTO dto) {
        Restaurant ex = rS.listId(id);
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un restaurante con ese ID.");
        }

        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(dto, Restaurant.class);
        r.setId(id); // Asegúrate de que el setter en tu Entidad se llame así
        rS.update(r);

        return ResponseEntity.ok("Restaurante actualizado correctamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Restaurant restaurant = rS.listId(id);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un restaurante con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Restaurante eliminado correctamente.");
    }
}