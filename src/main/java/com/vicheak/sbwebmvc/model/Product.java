package com.vicheak.sbwebmvc.model;

import lombok.*;

//POJO : requires getter, setter, and no-args constructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private String slug;
    private Double price;
    private Boolean inStock;
}
