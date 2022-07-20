package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.entities.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value = "")
    public UserEntity findUserBy(){
        return new UserEntity();
    }
}
