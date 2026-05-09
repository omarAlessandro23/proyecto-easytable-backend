package com.example.easytable.Controllers;

import com.example.easytable.Dtos.ReviewDTO;
import com.example.easytable.Dtos.ReviewQuery1DTO;
import com.example.easytable.Entities.Review;
import com.example.easytable.Serviceinterfaces.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")

public class ReviewController {

    @Autowired
    private IReviewService rS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/listar")
    public ResponseEntity<?> list() {

        List<Review> lista = rS.list();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen reviews registradas");
        }

        List<ReviewDTO> response = lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @PostMapping("/registrar")
    public ResponseEntity<String> insert(@RequestBody ReviewDTO dto){
        ModelMapper m = new ModelMapper();
        Review r = m.map(dto, Review.class);
        if (dto.getRestaurantId() <=0) {
            return ResponseEntity.badRequest()
                    .body("El restaurantId es obligatorio");
        }

        if (dto.getUserId() <=0) {
            return ResponseEntity.badRequest()
                    .body("El userId es obligatorio");
        }
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Review registrada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ReviewDTO dto){
        Review ex = rS.listId(id);
        if(ex == null || ex.getUserId()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una review con el ID");
        }
        ModelMapper m = new ModelMapper();
        Review r = m.map(dto, Review.class);
        r.setReviewId(id);
        rS.update(r);
        return ResponseEntity.ok("Review actualizada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        Review review = rS.listId(id);
        if(review == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una review con el ID" + id);

        }
        rS.delete(id);
        return ResponseEntity.ok("Review eliminada correctamente");
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/buscar-restaurante/{restaurantId}")
    public ResponseEntity<?>findByRestaurantId(@PathVariable int restaurantId) {
        if (restaurantId <= 0) {
            return ResponseEntity.badRequest()
                    .body("El ID del restaurante es inválido");
        }
        List<Review> lista = rS.findByRestaurantId(restaurantId);

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un restaurant con ID: " + restaurantId +
                            " o no tiene reviews registradas");
        }

        List<ReviewDTO> response = lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/rating-promedio")
    public ResponseEntity<?> getPromedioRating() {

        List<Object[]> lista = rS.promedioRatingPorRestaurante();

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen reviews registradas");
        }

        List<ReviewQuery1DTO> response = lista.stream().map(x -> {

            ReviewQuery1DTO dto = new ReviewQuery1DTO();
            dto.setRestaurante((String) x[0]);
            dto.setPromedio(((Number) x[1]).intValue());

            return dto;

        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
