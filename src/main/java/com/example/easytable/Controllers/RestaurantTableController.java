package com.example.easytable.Controllers;


import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Entities.RestaurantTable;
import com.example.easytable.Serviceinterfaces.IRestaurantService;
import com.example.easytable.Serviceinterfaces.IRestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant-tables")
public class RestaurantTableController {

    @Autowired
    private IRestaurantTableService rtS;

    @Autowired
    private IRestaurantService rS;

    @GetMapping("/lsitar")
    public List<RestaurantTable> listar(){
        return rtS.list();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody RestaurantTable restaurantTable){
        if(restaurantTable.getCapacity()<=0){
            throw new RuntimeException("la capacidad debe ser mayor a 0");
        }

        Restaurant restaurant= rS.listId(restaurantTable.getCapacity());
        restaurantTable.setRestaurant(restaurant);

        rtS.insert(restaurantTable);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id){
        rtS.delete(id);
    }

    @GetMapping("/lsitar-id/{id}")
    public RestaurantTable listarId(@PathVariable("id")int id){
        return rtS.listId(id);
    }
}
