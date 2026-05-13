package com.example.easytable.Controllers;

import com.example.easytable.Dtos.NotificacionDTO;
import com.example.easytable.Dtos.RestaurantCategoryDTO;
import com.example.easytable.Dtos.RestaurantCategoryQuery1DTO;
import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.RestaurantCategoryMap;
import com.example.easytable.Serviceinterfaces.IRestaurantCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantcategory")
public class RestaurantCategoryContoller {
    @Autowired
    private IRestaurantCategoryService rS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")

    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody RestaurantCategoryDTO dto) {

        ModelMapper m = new ModelMapper();
        RestaurantCategoryMap r = m.map(dto, RestaurantCategoryMap.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Restaurante con categoria registrada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/restaurantes-categoria")
    public List<RestaurantCategoryQuery1DTO> listar() {

        return rS.contarRestaurantesPorCategoria().stream().map(x -> {

            RestaurantCategoryQuery1DTO dto = new RestaurantCategoryQuery1DTO();

            dto.setNombreCategoria((String) x[0]);
            dto.setContador(((Long) x[1]).intValue());

            return dto;

        }).collect(Collectors.toList());
    }

}
