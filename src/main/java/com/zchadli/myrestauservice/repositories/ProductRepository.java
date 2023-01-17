package com.zchadli.myrestauservice.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zchadli.myrestauservice.entities.Category;
import com.zchadli.myrestauservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByTitleContaining(String title, Pageable pageable);
    Page<Product> findByTitleContainingAndCategory(String title, Category category, Pageable pageable);

    List<Product> findByCategoryIn(List<Category> categories);
    Page<Product> findByCategoryInAndTitleContaining(List<Category> categories, String title, Pageable pageable);
    
    
}
