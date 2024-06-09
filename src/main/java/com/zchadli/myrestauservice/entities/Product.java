package com.zchadli.myrestauservice.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String imagePath;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;
    @ManyToMany(mappedBy = "favoriteProducts", fetch = FetchType.EAGER)
    private Set<StoreUser> userFavoriteProduct=new HashSet<>();
    @OneToMany(mappedBy = "product")
    private Set<CartProduct> cardProducts = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if(this == object) return  true;
        if(object==null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return getId().equals(product.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
