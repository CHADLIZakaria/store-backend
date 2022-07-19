package com.zchadli.myrestauservice.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.CategoryService;
import com.zchadli.myrestauservice.dto.CategoryDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
//@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;
    
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public CategoryDto save(CategoryDto category, @RequestParam("file") MultipartFile file) {
        return categoryService.save(category, file);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public CategoryDto update(CategoryDto category, @PathVariable Long id, @RequestParam(name="file", required = false) MultipartFile file) {
        return categoryService.update(id, category, file);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
