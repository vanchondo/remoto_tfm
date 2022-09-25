package com.vanchondo.tfm.dtos;

import com.apptasticsoftware.rssreader.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RssItem extends Item {
    private String imgUrl;
}
