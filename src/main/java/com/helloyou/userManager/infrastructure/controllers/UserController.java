package com.helloyou.userManager.infrastructure.controllers;

import com.helloyou.userManager.app.useCases.NewUserUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/index")
    public String newUser(
            NewUserUseCase newUserUseCase
    ) {
        return newUserUseCase.execute();

    }
}
