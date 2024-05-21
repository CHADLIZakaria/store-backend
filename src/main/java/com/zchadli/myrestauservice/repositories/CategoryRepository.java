package com.zchadli.myrestauservice.repositories;

import java.util.List;

import com.zchadli.myrestauservice.dto.CategoryCountDto;
import com.zchadli.myrestauservice.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zchadli.myrestauservice.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findByNameContaining(String name);
    @Query(value = "SELECT new com.zchadli.myrestauservice.dto.CategoryCountDto(c.id, c.name, COUNT(p) as product_count) FROM Product p " +
            "JOIN p.category c " +
            "GROUP BY c.id")
    List<CategoryCountDto> countProductsByCategory();
    @Query(value = "SELECT new com.zchadli.myrestauservice.dto.CategoryCountDto(c.id, c.name, COUNT(p) as product_count) FROM Product p " +
            "JOIN p.category c " +
            "GROUP BY c.id")
    List<CategoryCountDto> countProductsByPrice();
}
