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
    public List<ReviewDTO> list() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @PostMapping("/registrar")
    public ResponseEntity<String> insert(@RequestBody ReviewDTO dto){
        ModelMapper m = new ModelMapper();
        Review r = m.map(dto, Review.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Review registrada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ReviewDTO dto){
        Review ex = rS.listId(id);
        if(ex != null){
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
        if(review != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una review con el ID" + id);

        }
        rS.delete(id);
        return ResponseEntity.ok("Review eliminada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/buscar-usuario/{userId}")
    public List<ReviewDTO> findByUserId(@PathVariable int userId){
        return rS.findByRestaurantId(userId).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @GetMapping("/buscar-restaurante/{restaurantId}")
    public List<ReviewDTO> findByRestaurantId(@PathVariable int restaurantId) {
        return rS.findByRestaurantId(restaurantId).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/rating-promedio")
    public List<ReviewQuery1DTO> getPromedioRating() {

        return rS.promedioRatingPorRestaurante().stream().map(x -> {

            ReviewQuery1DTO dto = new ReviewQuery1DTO();
            dto.setRestaurante((String) x[0]);
            dto.setPromedio(((Number) x[1]).intValue());

            return dto;

        }).collect(Collectors.toList());
    }
}
