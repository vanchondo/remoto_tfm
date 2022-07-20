package com.vanchondo.tfm.dtos.users;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DeleteUserDTO {

    @NotNull(message = "Username is required")
    @Size(min = 6, max = 25, message = "Username not valid min=6, max=25")
    private String username;

    @NotNull (message = "Password is required")
    @Size(min = 6, max = 50, message = "Password not valid, min=6, max=50")
    private String password;
}
