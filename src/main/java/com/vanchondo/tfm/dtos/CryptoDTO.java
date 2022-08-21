package com.vanchondo.tfm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDTO {
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
}
