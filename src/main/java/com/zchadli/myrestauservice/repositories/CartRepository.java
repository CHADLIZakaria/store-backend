package com.zchadli.myrestauservice.repositories;

import com.zchadli.myrestauservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {
    Optional<Cart> findByUserId(Long userId);
}
