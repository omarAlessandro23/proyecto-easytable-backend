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
@RequestMapping("/Restaurants")
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

    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody RestaurantDTO dto) {
        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(dto, Restaurant.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante registrado correctamente.");
    }

    @PutMapping("/actualizar/{id}")
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

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Restaurant restaurant = rS.listId(id);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un restaurante con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Restaurante eliminado correctamente.");
    }
    @GetMapping("/mejoresCalificados")
    public ResponseEntity<?> buscarTop(@RequestParam Double rating) {
        // Validación de rango lógico
        if (rating < 0 || rating > 5) {
            return new ResponseEntity<>("Error: La calificación debe ser un número entre 0 y 5", HttpStatus.BAD_REQUEST);
        }

        List<Restaurant> lista = rS.findByTopRating(rating);

        if (lista.isEmpty()) {
            return new ResponseEntity<>("No hay restaurantes con una calificación mayor o igual a " + rating, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // 3. Búsqueda por Cercanía (Geolocalización)
    @GetMapping("/cercanos")
    public ResponseEntity<?> buscarCercanos(@RequestParam Double lat, @RequestParam Double lng, @RequestParam Double dist) {
        // Validar que las coordenadas sean geográficamente posibles
        if (lat < -90 || lat > 90 || lng < -180 || lng > 180) {
            return new ResponseEntity<>("Coordenadas fuera de rango (Latitud -90 a 90, Longitud -180 a 180)", HttpStatus.BAD_REQUEST);
        }

        if (dist <= 0) {
            return new ResponseEntity<>("La distancia de búsqueda debe ser mayor a 0 km", HttpStatus.BAD_REQUEST);
        }

        List<Restaurant> lista = rS.findNearby(lat, lng, dist);

        if (lista.isEmpty()) {
            return new ResponseEntity<>("No se encontraron restaurantes en un radio de " + dist + "km", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}