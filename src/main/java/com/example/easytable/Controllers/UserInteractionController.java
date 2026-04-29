package com.example.easytable.Controllers;

import com.example.easytable.Serviceinterfaces.IUserInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinteraction")
public class UserInteractionController {
    @Autowired
    private IUserInteractionService UIS;


}
