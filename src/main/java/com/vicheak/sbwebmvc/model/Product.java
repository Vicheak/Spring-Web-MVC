package com.vicheak.sbwebmvc.model;

import lombok.*;

import java.util.List;

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
    private String description;
    private Double price;
    private Boolean inStock;

    //associated resources
    private Supplier supplier;
    private List<Category> categories;
}
