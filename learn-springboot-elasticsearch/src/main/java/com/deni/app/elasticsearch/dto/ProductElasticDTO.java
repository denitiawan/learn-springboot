package com.deni.app.elasticsearch.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductElasticDTO {
    String id;
    String name;
    String description;
    Double price;
}
