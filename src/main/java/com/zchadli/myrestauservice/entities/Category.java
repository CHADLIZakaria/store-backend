package com.zchadli.myrestauservice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imagePath;
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
    @Override
    public boolean equals(Object object) {
        if(this == object) return  true;
        if(object==null || getClass() != object.getClass()) return false;
        Category category = (Category) object;
        return getId().equals(category.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
