package com.zchadli.myrestauservice.business.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.dto.CategoryDto;

public interface CategoryService {
    public CategoryDto save(CategoryDto categoryDto, MultipartFile file);
    public CategoryDto update(Long idCategory, CategoryDto categoryDto, MultipartFile file);
    public List<CategoryDto> findAll();
    public void deleteById(Long id);
    public CategoryDto findById(Long id);
    public CategoryDto findByCategoryName(String categoryName);
    public Long getNumberCategories();
    public List<CategoryDto> searchCategories(String keyword);
}
