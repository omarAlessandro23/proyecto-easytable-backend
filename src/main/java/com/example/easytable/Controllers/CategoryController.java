package com.example.easytable.Controllers;

import com.example.easytable.Dtos.CategoryDTO;
import com.example.easytable.Dtos.RestaurantDTO;
import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Serviceinterfaces.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

    @Autowired
    private ICategoryService  cS;


    @GetMapping("/listar")
    public List<CategoryDTO> listar() {
        return cS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CategoryDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody CategoryDTO dto) {

        if (dto.getNombreCategoria() == null || dto.getNombreCategoria().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        }

        Category c = new Category();
        c.setNombreCategoria(dto.getNombreCategoria());

        cS.insert(c);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Categoria registrada correctamente.");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryDTO dto) {
        Category cat = cS.listId(id);
        if (cat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una categoria con ese ID.");
        }

        ModelMapper m = new ModelMapper();
        Category c = m.map(dto, Category.class);
        c.setIdCategory(id);
        cS.update(c);

        return ResponseEntity.ok("Categoria actualizada correctamente");
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Category c = cS.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una categoria con el ID: " + id);
        }
        cS.delete(id);
        return ResponseEntity.ok("Categoria eliminada correctamente.");
    }
}
