package com.zchadli.myrestauservice.business.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface ProductService {
    public ProductDto save(ProductDto product, MultipartFile file);
    public List<ProductDto> findAll();
    public void deleteById(Long id);
    public ProductDto findById(Long id);
    public PaginationResponse findSearch(int page, int size, String keyword, String categoryName);
    public Long getNumberPoruducts();
    public List<ProductDto> findByCategoryIn(String idsCategories);
}
