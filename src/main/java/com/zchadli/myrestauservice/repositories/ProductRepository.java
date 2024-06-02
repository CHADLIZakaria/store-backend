package com.zchadli.myrestauservice.repositories;

import java.util.List;

import com.zchadli.myrestauservice.dto.CategoryCountDto;
import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.dto.RangePriceCountDto;
import com.zchadli.myrestauservice.dto.ReviewCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zchadli.myrestauservice.entities.Category;
import com.zchadli.myrestauservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findByTitleContaining(String title, Pageable pageable);
    Page<Product> findByTitleContainingAndCategory(String title, Category category, Pageable pageable);

    List<Product> findByCategoryIn(List<Category> categories);
    Page<Product> findByCategoryInAndTitleContaining(List<Category> categories, String title, Pageable pageable);
    List<Product> findByIdNot(Long id);

    @Query("SELECT new com.zchadli.myrestauservice.dto.RangePriceCountDto(pr.id, pr.minPrice, pr.maxPrice, COUNT(p.id)) " +
            "FROM PriceRange pr " +
            "LEFT JOIN Product p ON p.price BETWEEN pr.minPrice AND COALESCE(pr.maxPrice, p.price) " +
            "GROUP BY pr.id, pr.minPrice, pr.maxPrice")
    List<RangePriceCountDto> countProductsByPrice();

    @Query("SELECT new com.zchadli.myrestauservice.dto.ReviewCountDto(r.rating, COUNT(id_product)) " +
            "FROM Review r " +
            "GROUP BY r.rating")
    List<ReviewCountDto> countProductsByReview();
}
