package com.zchadli.myrestauservice.business.service;

import com.zchadli.myrestauservice.dto.CartDto;
import com.zchadli.myrestauservice.entities.OperationEnum;

import java.util.List;

public interface CartService {
    void addProduct(String username, Long idProduct, OperationEnum operationEnum);
    List<CartDto> searchCarts(String username);
}
