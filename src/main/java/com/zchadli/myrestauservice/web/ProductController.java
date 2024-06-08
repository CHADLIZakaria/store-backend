package com.zchadli.myrestauservice.web;

import java.util.List;

import com.zchadli.myrestauservice.dto.RangePriceCountDto;
import com.zchadli.myrestauservice.dto.ReviewCountDto;
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
        @RequestParam(name="size", defaultValue = "") Integer size,
        @RequestParam(name="id", defaultValue = "") Long id,
        @RequestParam(name="keyword", defaultValue = "") String keyword,
        @RequestParam(name="user", defaultValue = "") String user,
        @RequestParam(name="categories", defaultValue = "", required = false) List<Integer> categories,
        @RequestParam(name="categoryName", defaultValue = "") String categoryName,
        @RequestParam(name="minPrice", required = false) Double minPrice,
        @RequestParam(name="maxPrice", required = false) Double maxPrice,
        @RequestParam(name="review", required = false) Integer review,
        @RequestParam(name="sort", defaultValue = "id") String sort,
        @RequestParam(name="direction", defaultValue = "asc") String direction
    ) {
        return productService.findSearch(page, size, id, user, keyword, categories, categoryName, minPrice, maxPrice, review, sort, direction);
    }
    @RequestMapping(value = "/products/{username}/favorites", method = RequestMethod.GET)
    public List<ProductDto> findFavorites(@PathVariable String username) {
        return productService.findFavorites(username);
    }

    @RequestMapping(value = "/products/similar/{id}/{idCategory}", method = RequestMethod.GET)
    public List<ProductDto> findSimilarProducts(@PathVariable Long id, @PathVariable Long idCategory) {
        return productService.findSimilarProducts(id, idCategory);
    }

    @RequestMapping(value = "/products/prices/count", method = RequestMethod.GET)
    public List<RangePriceCountDto> productCountByCategory() {
        return productService.productCountByPriceRange();
    }

    @RequestMapping(value = "/products/reviews/count", method = RequestMethod.GET)
    public List<ReviewCountDto> productCountByReview() {
        return productService.productCountByReview();
    }

}
