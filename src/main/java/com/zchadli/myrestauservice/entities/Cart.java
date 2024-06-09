package com.zchadli.myrestauservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="id_user")
    private StoreUser user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cardProducts = new ArrayList<>();
}
