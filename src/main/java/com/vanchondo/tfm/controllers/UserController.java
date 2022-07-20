package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.dtos.users.SaveUserDTO;
import com.vanchondo.tfm.dtos.users.UserDTO;
import com.vanchondo.tfm.entities.UserEntity;
import com.vanchondo.tfm.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "")
    public UserEntity findUserBy(){
        return new UserEntity();
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody SaveUserDTO user){
        UserDTO dto = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
