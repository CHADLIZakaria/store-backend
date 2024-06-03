package com.zchadli.myrestauservice.business.service;

import java.util.List;

import com.zchadli.myrestauservice.dto.RangePriceCountDto;
import com.zchadli.myrestauservice.dto.ReviewCountDto;
import com.zchadli.myrestauservice.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface ProductService {
    ProductDto save(ProductDto product, MultipartFile file);
    List<ProductDto> findAll();
    void deleteById(Long id);
    ProductDto findById(Long id);
    PaginationResponse findSearch(int page, int size, Long id, String user, String keyword, List<Integer> categories, Double minPrice, Double maxPrice, Integer review, String sortField, String sortDirection);
    Long getNumberPoruducts();
    List<ProductDto> findByCategoryIn(String idsCategories);
    List<ProductDto> findByIdNot(Long id);
    List<RangePriceCountDto> productCountByPriceRange();
    List<ReviewCountDto> productCountByReview();
}
