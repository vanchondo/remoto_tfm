package com.vanchondo.tfm.dtos.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class CurrentUserDTO {
    private String iss;
    private String username;
    private String email;
    private String role;
    private List<String> authorities;
    private Date iat;
    private Date exp;
}
