package com.zchadli.myrestauservice.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.ProductService;
import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ProductController {
    
    private final ProductService productService;
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findAll(@RequestParam(defaultValue = "", required = false) String idsCategory) {
        return productService.findByCategoryIn(idsCategory);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public List<ProductDto> findAll(@PathVariable Long id) {
        return productService.findByIdNot(id);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ProductDto save(ProductDto productDto, @RequestParam("file") MultipartFile file) {
        return productService.save(productDto, file);
    }

    @RequestMapping(value = "/admin/product/{id}", method = RequestMethod.PUT)
    public ProductDto update(ProductDto productDto, @PathVariable Long id, @RequestParam("file") MultipartFile file) {
        productDto.setId(id);
        return productService.save(productDto, file);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
    

    @RequestMapping(value="/products/search", method = RequestMethod.GET)
    public PaginationResponse search(
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "3") int size,
        @RequestParam(name="keyword", defaultValue = "") String keyword,
        @RequestParam(name="categories", defaultValue = "", required = false) List<Integer> categories,
        @RequestParam(name="minPrice", required = false) Double minPrice,
        @RequestParam(name="maxPrice", required = false) Double maxPrice,
        @RequestParam(name="sort", defaultValue = "id") String sortField,
        @RequestParam(name="direction", defaultValue = "asc") String sortDirection
    ) {
        return productService.findSearch(page, size, keyword, categories, minPrice, maxPrice, sortField, sortDirection);
    }
}
