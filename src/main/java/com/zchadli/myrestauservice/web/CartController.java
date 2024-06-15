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
        @RequestParam(name="username", value = "") String username
    ) {
        return cartService.searchCarts(username);
    }

    @RequestMapping(value = "/cart/add/{idProduct}", method = RequestMethod.POST)
    public void addProduct(@PathVariable Long idProduct, @RequestBody String username) {
        cartService.addProduct(username, idProduct, OperationEnum.ADD);
    }
    @RequestMapping(value = "/cart/remove/{idProduct}", method = RequestMethod.POST)
    public void removeProduct(@PathVariable Long idProduct, @RequestBody String username) {
        cartService.addProduct(username, idProduct, OperationEnum.REMOVE);
    }
}
