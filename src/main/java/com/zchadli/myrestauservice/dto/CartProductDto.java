package com.zchadli.myrestauservice.dto;

import lombok.Data;

@Data
public class CartProductDto {
    private Long id;
    private String title;
    private String imagePath;
    private Double price;
    private Integer quantity;
}
