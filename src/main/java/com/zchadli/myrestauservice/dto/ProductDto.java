package com.zchadli.myrestauservice.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imagePath;
    private CategoryDto category;
    private boolean inFavorites;
}
