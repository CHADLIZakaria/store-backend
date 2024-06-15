package com.zchadli.myrestauservice.dto;

import lombok.Data;

@Data
public class CartProductDto {
    private Long id;
    private String title;
    private String imagePath;
    private Long idProduct;
    private Double price;
    private Integer quantity;
    private String categoryName;
}
