package com.zchadli.myrestauservice.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_cart")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
    private Integer quantity;
}
