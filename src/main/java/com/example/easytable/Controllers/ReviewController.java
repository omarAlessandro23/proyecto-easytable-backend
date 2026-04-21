package com.example.easytable.Controllers;

import com.example.easytable.Dtos.ReviewDTO;
import com.example.easytable.Entities.Review;
import com.example.easytable.Serviceinterfaces.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")

public class ReviewController {

    @Autowired
    private IReviewService rS;

    @GetMapping
    public List<ReviewDTO> list() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping("/register")
    public ResponseEntity<String> insert(@RequestBody ReviewDTO dto){
        ModelMapper m = new ModelMapper();
        Review r = m.map(dto, Review.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Review registrada correctamente");
    }
    @PutMapping("/update/{id}")
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        Review review = rS.listId(id);
        if(review != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una review con el ID" + id);

        }
        rS.delete(id);
        return ResponseEntity.ok("Review eliminada correctamente");
    }
    @GetMapping("/usuario/{userId}")
    public List<ReviewDTO> findByUserId(@PathVariable int userId){
        return rS.findByUserId(userId).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/restaurante/{restaurantId}")
    public List<ReviewDTO> findByRestaurantId(@PathVariable int restaurantId) {
        return rS.findByRestaurantId(restaurantId).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/rating-promedio")
    public List<ReviewDTO> getPromedioRating() {

        return rS.promedioRatingPorRestaurante().stream().map(x -> {

            ReviewDTO dto = new ReviewDTO();

            // ⚠️ No hay review real, así que esto es adaptado
            dto.setComment((String) x[0]); // nombre del restaurante
            dto.setRating(((Number) x[1]).intValue()); // promedio convertido a int

            return dto;

        }).collect(Collectors.toList());
    }
}
