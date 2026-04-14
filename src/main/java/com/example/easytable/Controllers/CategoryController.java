package com.example.easytable.Controllers;

import com.example.easytable.Dtos.CategoryDTO;
import com.example.easytable.Entities.Category;
import com.example.easytable.Serviceinterfaces.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private ICategoryService CS;

    @GetMapping("/listar")
    public List<CategoryDTO> listar() {
        return CS.list().stream().map( x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CategoryDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertar(@RequestBody CategoryDTO dto) {
        ModelMapper m = new ModelMapper();
        Category c = m.map(dto, Category.class);
        CS.insert(c);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Categoria registrada correctamente.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryDTO dto) {
        Category ex = CS.listId(id);
        if (ex == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una categoria con ese ID.");
        ModelMapper m = new ModelMapper();
        Category c = m.map(dto, Category.class);
        c.setIdCategory(id);
        CS.update(c);

        return ResponseEntity.ok("Categoria actualizada correctamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Category category = CS.listId(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una categoria con el ID: " + id);
        }
        CS.delete(id);
        return ResponseEntity.ok("Categoria eliminda correctamente.");
    }
}
