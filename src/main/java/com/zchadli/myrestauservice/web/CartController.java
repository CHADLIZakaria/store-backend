package com.zchadli.myrestauservice.web;

import com.zchadli.myrestauservice.business.service.CartService;
import com.zchadli.myrestauservice.business.service.CategoryService;
import com.zchadli.myrestauservice.dto.CartDto;
import com.zchadli.myrestauservice.dto.CartProductDto;
import com.zchadli.myrestauservice.dto.CategoryDto;
import com.zchadli.myrestauservice.entities.OperationEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class CartController {
    private final CartService cartService;

    @RequestMapping(value = "/carts/search")
    public List<CartDto> searchCarts(
        @RequestParam(name="username", value = "", required = false) String username
    ) {
        return cartService.searchCarts(username);
    }

    @RequestMapping(value = "/carts/add/{idProduct}", method = RequestMethod.POST)
    public List<CategoryDto> addProduct(@PathVariable Long idProduct, @RequestBody Long idUser) {
        cartService.addProduct(idUser, idProduct, OperationEnum.ADD);
        return null;
    }
    @RequestMapping(value = "/carts/remove/{idProduct}", method = RequestMethod.POST)
    public List<CategoryDto> removeProduct(@PathVariable Long idProduct, @RequestBody Long idUser) {
        cartService.addProduct(idUser, idProduct, OperationEnum.REMOVE);
        return null;
    }
}
