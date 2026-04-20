package com.example.easytable.Controllers;

import com.example.easytable.Serviceinterfaces.IFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {
    @Autowired
    private IFavoritoService fS;


}
