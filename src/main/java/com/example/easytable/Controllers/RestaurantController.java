package com.example.easytable.Controllers;

import com.example.easytable.Dtos.RestaurantDTO;
import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Serviceinterfaces.ICategoryService;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService rS;
    @Autowired
    private  ICategoryService cS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
    @GetMapping("/listar")
    public List<RestaurantDTO> listar() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RestaurantDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody RestaurantDTO dto) {

        // VALIDAR NOMBRE
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El nombre del restaurante es obligatorio.");
        }

        // VALIDAR WEB URL
        if (dto.getWebUrl() != null &&
                !dto.getWebUrl().startsWith("https://")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: La página web debe comenzar con https://");
        }

        // VALIDAR GOOGLE MAPS URL
        if (dto.getGoogleMapsUrl() != null &&
                !dto.getGoogleMapsUrl().startsWith("https://")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El enlace de Google Maps debe comenzar con https://");
        }

        // VALIDAR RATING
        if (dto.getRatingAvg() != null &&
                (dto.getRatingAvg() < 0 || dto.getRatingAvg() > 5)) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El rating debe estar entre 0 y 5.");
        }
        // VALIDAR LATITUD
        if (dto.getLatitude() == null ||
                dto.getLatitude() < -90 ||
                dto.getLatitude() > 90) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: La latitud debe estar entre -90 y 90.");
        }

// VALIDAR LONGITUD
        if (dto.getLongitude() == null ||
                dto.getLongitude() < -180 ||
                dto.getLongitude() > 180) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: La longitud debe estar entre -180 y 180.");
        }

        Category category = cS.listId(dto.getIdCategory());

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: categoria no encontrada.");
        }

        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(dto, Restaurant.class);

        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante registrado correctamente.");
    }
   @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
   @PutMapping("/actualizar/{id}")
   public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody RestaurantDTO dto) {
       // 1. Intentar obtener el restaurante existente
       Restaurant restauranteExistente = rS.listId(id);

       if (restauranteExistente == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("No existe un restaurante con el ID: " + id);
       }

       ModelMapper m = new ModelMapper();
       m.getConfiguration().setSkipNullEnabled(true);

       m.map(dto, restauranteExistente);

       restauranteExistente.setId(id);

       rS.update(restauranteExistente);

       return ResponseEntity.ok("Restaurante actualizado correctamente");
   }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
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