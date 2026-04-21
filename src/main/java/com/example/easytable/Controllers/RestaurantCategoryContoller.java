package com.example.easytable.Controllers;

import com.example.easytable.Dtos.RestaurantCategoryDTO;
import com.example.easytable.Entities.Category;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Serviceinterfaces.IRestaurantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantcategory")
public class RestaurantCategoryContoller {
    @Autowired
    private IRestaurantCategoryService rS;

    @GetMapping("/restaurantes-por-categoria")
    public List<RestaurantCategoryDTO> listar() {

        return rS.restaurantxcategoria().stream().map(x -> {

            RestaurantCategoryDTO dto = new RestaurantCategoryDTO();

            Category c = new Category();
            c.setNombreCategoria((String) x[0]);

            Restaurant r = new Restaurant();
            r.setName((String) x[1]);

            dto.setCategory(c);
            dto.setRestaurant(r);

            return dto;

        }).collect(Collectors.toList());
    }
}
