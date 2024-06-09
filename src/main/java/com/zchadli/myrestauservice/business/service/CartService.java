package com.zchadli.myrestauservice.business.service;

import com.zchadli.myrestauservice.dto.CartDto;
import com.zchadli.myrestauservice.entities.OperationEnum;

import java.util.List;

public interface CartService {
    void addProduct(Long idUser, Long idProduct, OperationEnum operationEnum);
    List<CartDto> searchCarts(String username);
}
