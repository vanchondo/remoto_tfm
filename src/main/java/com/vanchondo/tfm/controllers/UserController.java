package com.vanchondo.tfm.controllers;

import com.vanchondo.tfm.dtos.users.DeleteUserDTO;
import com.vanchondo.tfm.dtos.users.SaveUserDTO;
import com.vanchondo.tfm.dtos.users.UpdateUserDTO;
import com.vanchondo.tfm.dtos.users.UserDTO;
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

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody SaveUserDTO user){
        UserDTO dto = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserDTO user){
        UserDTO dto = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping(value = "")
    public void deleteUSer(@Valid @RequestBody DeleteUserDTO user){
        userService.deleteUser(user);
    }
}
