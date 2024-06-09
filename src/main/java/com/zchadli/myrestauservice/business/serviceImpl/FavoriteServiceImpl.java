package com.zchadli.myrestauservice.business.serviceImpl;

import com.zchadli.myrestauservice.business.service.FavoriteService;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.StoreUser;
import com.zchadli.myrestauservice.mapper.StoreMapper;
import com.zchadli.myrestauservice.repositories.ProductRepository;
import com.zchadli.myrestauservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService  {
    private final ProductRepository productRepository;
    private  final UserRepository userRepository;
    private final StoreMapper mapper;

    @Override
    public void addFavorite(String username, Long idProduct) {
        Product product = productRepository.findById(idProduct).orElseThrow(RuntimeException::new);
        StoreUser user = userRepository.findByUsername(username);
        user.getFavoriteProducts().add(product);
        userRepository.save(user);
    }

    @Override
    public void removeFavorite(String username, Long idProduct) {
        Product product = productRepository.findById(idProduct).orElseThrow(RuntimeException::new);
        StoreUser user = userRepository.findByUsername(username);
        user.getFavoriteProducts().remove(product);
        userRepository.save(user);
    }
}
