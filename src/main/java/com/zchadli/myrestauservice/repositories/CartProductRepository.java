package com.zchadli.myrestauservice.repositories;

import com.zchadli.myrestauservice.entities.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long>  {
}
