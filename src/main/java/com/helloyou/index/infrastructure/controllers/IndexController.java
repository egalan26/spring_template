package com.helloyou.index.infrastructure.controllers;

import com.helloyou.userManager.app.useCases.NewUserUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping("/index")
    public String index() {


        return "Hello world!";

    }

}