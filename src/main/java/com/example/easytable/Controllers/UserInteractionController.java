package com.example.easytable.Controllers;

import com.example.easytable.Dtos.RestaurantCategoryDTO;
import com.example.easytable.Dtos.UserInteractionDTO;
import com.example.easytable.Entities.RestaurantCategoryMap;
import com.example.easytable.Entities.UserInteraction;
import com.example.easytable.Serviceinterfaces.IUserInteractionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinteraction")
public class UserInteractionController {
    @Autowired
    private IUserInteractionService UIS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")

    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody UserInteractionDTO dto) {

        ModelMapper m = new ModelMapper();
        UserInteraction u = m.map(dto, UserInteraction.class);
        UIS.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Interaccion de usuario registrada correctamente.");
    }


}
