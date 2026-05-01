package com.example.easytable.Controllers;

import com.example.easytable.Dtos.FavoritoDTO;
import com.example.easytable.Dtos.RestaurantCategoryDTO;
import com.example.easytable.Entities.Favoritos;
import com.example.easytable.Entities.RestaurantCategoryMap;
import com.example.easytable.Serviceinterfaces.IFavoritoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {
    @Autowired
    private IFavoritoService fS;

    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody FavoritoDTO dto) {

        ModelMapper m = new ModelMapper();
        Favoritos r = m.map(dto, Favoritos.class);
        fS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Favorito registrado correctamente.");
    }
}
