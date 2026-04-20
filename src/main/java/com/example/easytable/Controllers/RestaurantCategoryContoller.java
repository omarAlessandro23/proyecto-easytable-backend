package com.example.easytable.Controllers;

import com.example.easytable.Serviceinterfaces.IRestaurantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantcategory")
public class RestaurantCategoryContoller {
    @Autowired
    private IRestaurantCategoryService rc;
}
