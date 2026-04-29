package com.example.easytable.Controllers;

import com.example.easytable.Dtos.FavoriteDTO;
import com.example.easytable.Entities.Favorite;
import com.example.easytable.Serviceinterfaces.IFavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService FS;

    @GetMapping("/listar")
    public List<FavoriteDTO> listar() {
        return FS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, FavoriteDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertar(@RequestBody FavoriteDTO dto) {
        ModelMapper m = new ModelMapper();
        Favorite f = m.map(dto, Favorite.class);
        FS.insert(f);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante añadido a favoritos correctamente.");
    }

    @GetMapping("/listar/{idUsuario}/{idRestaurant}")
    public ResponseEntity<?> listarId(@PathVariable("idUsuario") Integer idU, @PathVariable("idRestaurant") Integer idR) {
        Favorite f = FS.listId(idU, idR);
        if (f == null || f.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el favorito especificado.");
        }
        ModelMapper m = new ModelMapper();
        FavoriteDTO dto = m.map(f, FavoriteDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable("idUsuario") Integer idU) {
        List<Favorite> lista = FS.listByUser(idU);

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron favoritos para el usuario con ID: " + idU);
        }
        List<FavoriteDTO> listaDTO = lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, FavoriteDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @DeleteMapping("/delete/{idUsuario}/{idRestaurant}")
    public ResponseEntity<String> eliminar(@PathVariable("idUsuario") Integer idU, @PathVariable("idRestaurant") Integer idR) {
        Favorite f = FS.listId(idU, idR);
        if (f == null || f.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el favorito para eliminar.");
        }
        FS.delete(idU, idR);
        return ResponseEntity.ok("Favorito eliminado correctamente.");
    }
}
