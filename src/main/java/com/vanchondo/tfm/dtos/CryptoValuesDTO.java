package com.vanchondo.tfm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoValuesDTO {
    private List<CryptoDTO> history;
    private List<CryptoDTO> predictions;
}
