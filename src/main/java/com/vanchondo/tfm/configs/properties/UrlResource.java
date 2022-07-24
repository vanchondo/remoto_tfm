package com.vanchondo.tfm.configs.properties;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlResource {
    private String url;
    private List<String> methods;
}
