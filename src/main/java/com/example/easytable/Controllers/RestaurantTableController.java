package com.example.easytable.Controllers;


import com.example.easytable.Dtos.RestaurantDTO;
import com.example.easytable.Dtos.RestaurantTableDTO;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.RestaurantTable;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IRestaurantTableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant-tables")
public class RestaurantTableController {

    @Autowired
    private IRestaurantTableService rtS;

    @Autowired
    private IRestaurantService rS;
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/listar")
    public List<RestaurantTable> listar(){
        return rtS.list();
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @PostMapping("/insertar")
    public ResponseEntity<String> insertar(@RequestBody RestaurantTableDTO dto) {

        ModelMapper m = new ModelMapper();

        RestaurantTable rt = m.map(dto, RestaurantTable.class);

        rtS.insert(rt);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Mesa registrada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id){
        rtS.delete(id);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")

    @GetMapping("/listar-id/{id}")
    public RestaurantTable listarId(@PathVariable("id")int id){
        return rtS.listId(id);
    }
}
