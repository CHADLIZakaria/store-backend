package com.zchadli.myrestauservice.web;

import com.zchadli.myrestauservice.business.service.FavoriteService;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.repositories.ProductRepository;
import com.zchadli.myrestauservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @RequestMapping(value = "/add/{username}/{idProduct}")
    public void addFavorite(@PathVariable String username, @PathVariable Long idProduct) {
       favoriteService.addFavorite(username, idProduct);
    }
}
