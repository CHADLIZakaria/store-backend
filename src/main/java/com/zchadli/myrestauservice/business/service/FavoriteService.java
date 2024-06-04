package com.zchadli.myrestauservice.business.service;

import com.zchadli.myrestauservice.dto.ProductDto;
import java.util.List;

public interface FavoriteService {
    public void addFavorite(String username, Long idProduct);
    public void removeFavorite(String username, Long idProduct);

}
