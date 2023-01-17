package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.CategoryService;
import com.zchadli.myrestauservice.business.service.FileService;
import com.zchadli.myrestauservice.dto.CategoryDto;
import com.zchadli.myrestauservice.entities.Category;
import com.zchadli.myrestauservice.exceptions.BusinessException;
import com.zchadli.myrestauservice.exceptions.ErrorsMessage;
import com.zchadli.myrestauservice.mapper.RestauMapper;
import com.zchadli.myrestauservice.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileService fileService;
    private final RestauMapper mapper;

    @Override
    public CategoryDto save(CategoryDto categoryDto, MultipartFile file) {
        String filePath = fileService.save(file);
        categoryDto.setImagePath(filePath);
        Category category = mapper.toCategory(categoryDto);
        return mapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> findAll() {
        return mapper.toCategoriesDto(categoryRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        CategoryDto categoryDto = findById(id);
        fileService.deleteFile(categoryDto.getImagePath());
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorsMessage.CATEGORY_NOT_FOUND));
        return mapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto findByCategoryName(String categoryName) {
        return mapper.toCategoryDto(categoryRepository.findByName(categoryName));
    }

    @Override
    public CategoryDto update(Long idCategory, CategoryDto categoryDto, MultipartFile file) {
        CategoryDto oldCategoryDto = findById(idCategory);
        String filePath = fileService.update(oldCategoryDto.getImagePath(), file);
        categoryDto.setImagePath(filePath);
        Category category = mapper.toCategory(categoryDto);
        return mapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public Long getNumberCategories() {
        return categoryRepository.count();
    }

    @Override
    public List<CategoryDto> searchCategories(String keyword) {
        return mapper.toCategoriesDto(categoryRepository.findByNameContaining(keyword));
    }

    
    
}
