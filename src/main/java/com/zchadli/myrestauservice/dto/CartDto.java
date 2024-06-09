package com.zchadli.myrestauservice.dto;

import com.zchadli.myrestauservice.entities.CartProduct;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Long id;
    private Long userId;
    private List<CartProductDto> products;
}
