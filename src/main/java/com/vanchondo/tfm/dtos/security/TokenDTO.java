package com.vanchondo.tfm.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {
    private String token;
}
