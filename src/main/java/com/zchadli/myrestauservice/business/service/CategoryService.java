package com.zchadli.myrestauservice.business.service;

import java.util.List;

import com.zchadli.myrestauservice.dto.CategoryCountDto;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.dto.CategoryDto;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto, MultipartFile file);
    CategoryDto update(Long idCategory, CategoryDto categoryDto, MultipartFile file);
    List<CategoryDto> findAll();
    void deleteById(Long id);
    CategoryDto findById(Long id);
    CategoryDto findByCategoryName(String categoryName);
    Long getNumberCategories();
    List<CategoryDto> searchCategories(String keyword);
    List<CategoryCountDto> productCountByCategory();
}
