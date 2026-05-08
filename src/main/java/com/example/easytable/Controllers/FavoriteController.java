package com.example.easytable.Controllers;

import com.example.easytable.Dtos.FavoriteDTO;
import com.example.easytable.Dtos.RestaurantDTO;
import com.example.easytable.Entities.Favorite;
import com.example.easytable.Serviceinterfaces.IFavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService FS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/listar")
    public List<FavoriteDTO> listar() {
        return FS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, FavoriteDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/register")
    public ResponseEntity<String> insertar(@RequestBody FavoriteDTO dto) {
        ModelMapper m = new ModelMapper();
        Favorite f = m.map(dto, Favorite.class);
        FS.insert(f);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante añadido a favoritos correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/check/{uId}/{rId}")
    public boolean checkFavorite(@PathVariable int uId, @PathVariable int rId) {
        return FS.esFavorito(uId, rId);
    }

    @GetMapping("/user/{uId}")
    public List<RestaurantDTO> getFavoritesByUser(@PathVariable int uId) {
        ModelMapper m = new ModelMapper();
        return FS.listarFavoritosPorUsuario(uId).stream()
                .map(x -> m.map(x, RestaurantDTO.class))
                .collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/suggestions/{uId}")
    public List<RestaurantDTO> getSuggestions(@PathVariable int uId) {
        ModelMapper m = new ModelMapper();
        return FS.obtenerSugerencias(uId).stream()
                .map(x -> m.map(x, RestaurantDTO.class))
                .collect(Collectors.toList());
    }
}
