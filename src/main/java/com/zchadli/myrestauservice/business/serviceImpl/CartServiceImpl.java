package com.zchadli.myrestauservice.business.serviceImpl;

import com.zchadli.myrestauservice.business.service.CartService;
import com.zchadli.myrestauservice.dto.CartDto;
import com.zchadli.myrestauservice.entities.*;
import com.zchadli.myrestauservice.mapper.StoreMapper;
import com.zchadli.myrestauservice.repositories.CartRepository;
import com.zchadli.myrestauservice.repositories.ProductRepository;
import com.zchadli.myrestauservice.repositories.UserRepository;
import com.zchadli.myrestauservice.specification.CartSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StoreMapper mapper;
    @Override
    @Transactional
    public void addProduct(String username, Long idProduct, OperationEnum operationEnum) {
        Cart cart = cartRepository.findByUserUsernameAndIsActive(username, true).orElseGet(() -> {
            Cart cartNotFound = new Cart();
            cartNotFound.setUser(userRepository.findByUsername(username));
            cartNotFound.setActive(true);
            return cartRepository.save(cartNotFound);
        });
        Product product = productRepository.findById(idProduct).orElse(new Product());
        Optional<CartProduct> existingProduct = cart.getCartProducts().stream().filter(cartProduct -> cartProduct.getProduct().getId().equals(idProduct)).findFirst();
        if(existingProduct.isPresent()) {
            CartProduct cartProduct = existingProduct.get();
            if(operationEnum == OperationEnum.ADD) {
                cartProduct.setQuantity(cartProduct.getQuantity()+1);
            }
            else if(operationEnum == OperationEnum.REMOVE && cartProduct.getQuantity() > 0) {
                cartProduct.setQuantity(cartProduct.getQuantity()-1);
            }
        }
        else {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProduct(product);
            cartProduct.setCart(cart);
            cartProduct.setQuantity(1);
            cart.getCartProducts().add(cartProduct);
        }
        cartRepository.save(cart);
    }

    @Override
    public List<CartDto> searchCarts(String username) {
        Specification<Cart> specification = Specification.where(CartSpecification.hasUsername(username));
        List<Cart> carts = cartRepository.findAll(specification);
        return mapper.toCartsDto(carts);
    }
}
