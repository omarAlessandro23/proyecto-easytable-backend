package com.example.easytable.Controllers;

import com.example.easytable.Dtos.FavoriteDTO;
import com.example.easytable.Dtos.FavoriteListDTO;
import com.example.easytable.Dtos.ReservationListDTO;
import com.example.easytable.Dtos.RestaurantDTO;
import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Serviceinterfaces.IFavoriteService;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService FS;
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private IUsuarioService uS;
    private final ModelMapper modelMapper = new ModelMapper();
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        // Llamamos al servicio una sola vez y guardamos el resultado
        List<Favorite> listaEntidad = FS.list();

        // 1. Validación: Si la lista es nula o está vacía, mandamos el mensaje
        if (listaEntidad == null || listaEntidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Aún no tienes restaurantes en tu lista de favoritos.");
        }

        // 2. Si hay datos, transformamos a DTO
        List<FavoriteDTO> listaDTO = listaEntidad.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, FavoriteDTO.class);
        }).collect(Collectors.toList());

        // 3. Retornamos la lista de DTOs
        return ResponseEntity.ok(listaDTO);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/register")
    public ResponseEntity<String> insertar(@Valid @RequestBody FavoriteDTO dto) {
        // 1. Validar que el Restaurante exista
        Restaurant res = restaurantService.listId(dto.getRestaurantid());
        if (res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El restaurante con ID " + dto.getRestaurantid() + " no existe.");
        }

        // 2. Validar que el Usuario exista
        Usuario user = uS.listId(dto.getUsuarioid());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El usuario con ID " + dto.getUsuarioid() + " no existe.");
        }

        // 3. Mapeo manual (más seguro que ModelMapper para relaciones)
        Favorite f = new Favorite();
        f.setComentario(dto.getComentario());
        f.setRestaurant(res); // Asignamos la entidad completa
        f.setUsuario(user);      // Asignamos la entidad completa

        FS.insert(f);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante añadido a favoritos correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable("idUsuario") Integer idU) {

        // 1. Validación Real: ¿Existe el usuario?
        Usuario usu = uS.listId(idU);
        if (usu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No existe un usuario con el ID " + idU);
        }

        // 2. Obtener lista
        List<Favorite> lista = FS.listByUser(idU);

        // 3. Validación de lista vacía
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok("El usuario " + usu.getIdUsuario() + " no tiene favoritos.");
        }

        // 4. Mapeo Correcto a DTO
        ModelMapper m = new ModelMapper();
        List<FavoriteDTO> listaDTO = lista.stream().map(x -> {
            FavoriteDTO dto = m.map(x, FavoriteDTO.class);

            // Mapeo explícito de IDs (evita que salgan en 0)
            dto.setUsuarioid(x.getUsuario().getIdUsuario());
            dto.setRestaurantid(x.getRestaurant().getId());

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Favorite favorite = FS.listId(id);
        if (favorite == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un restaurante con el ID: " + id);
        }
        FS.delete(id);
        return ResponseEntity.ok("Restaurante eliminado correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")

    @GetMapping("/ranking")
    public ResponseEntity<?> obtenerRankingRestaurantes() {
        List<Object[]> ranking = FS.countFavoritesByRestaurant();

        // Transformamos el Object[] en una estructura clara para el JSON
        List<Map<String, Object>> listaFinal = ranking.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            Restaurant r = (Restaurant) obj[0];
            map.put("restaurantId", r.getId());
            map.put("nombre", r.getName());
            map.put("totalFavoritos", obj[1]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaFinal);
    }
}
