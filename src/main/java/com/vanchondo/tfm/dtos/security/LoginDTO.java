package com.vanchondo.tfm.dtos.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
}
