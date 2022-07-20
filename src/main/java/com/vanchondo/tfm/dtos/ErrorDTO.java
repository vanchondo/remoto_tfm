package com.vanchondo.tfm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String error;
    private int statusCode;
    private List<String> messages;
}
