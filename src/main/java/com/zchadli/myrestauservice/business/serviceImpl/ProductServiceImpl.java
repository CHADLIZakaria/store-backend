package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.zchadli.myrestauservice.dto.*;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.repositories.UserRepository;
import com.zchadli.myrestauservice.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.CategoryService;
import com.zchadli.myrestauservice.business.service.FileService;
import com.zchadli.myrestauservice.business.service.ProductService;
import com.zchadli.myrestauservice.entities.PaginationResponse;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.exceptions.BusinessException;
import com.zchadli.myrestauservice.exceptions.ErrorsMessage;
import com.zchadli.myrestauservice.mapper.StoreMapper;
import com.zchadli.myrestauservice.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final StoreMapper mapper;
    private final FileService fileService;

    @Override
    public ProductDto save(ProductDto productDto, MultipartFile file) {
        // Save File
        String filePath = fileService.save(file);
        productDto.setImagePath(filePath);

        Product product = mapper.toProduct(productDto);
        return mapper.toProductDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) return new ArrayList<>();
        return mapper.toProductsDto(productRepository.findAll());
    }

    public List<ProductDto> findByIdNot(Long id) {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) return new ArrayList<>();
        return mapper.toProductsDto(productRepository.findByIdNot(id));
    }

    @Override
    public void deleteById(Long id) {
        ProductDto productDto = findById(id);
        fileService.deleteFile(productDto.getImagePath());
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorsMessage.PRODUCT_NOT_FOUND));
        return mapper.toProductDto(product);
    }

    @Override
    public Long getNumberPoruducts() {
        return productRepository.count();
    }

    @Override
    public PaginationResponse findProductsWithFavorites(String username, int page, int size, String keyword, List<Integer> categories, Double minPrice, Double maxPrice, Integer review, String sortField, String sortDirection) {
        RestauUser user = userRepository.findByUsername(username);
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasCategory(categories))
                .and(ProductSpecification.hasKeyword(keyword))
                .and(ProductSpecification.minPrice(minPrice))
                .and(ProductSpecification.maxPrice(maxPrice))
                .and(ProductSpecification.review(review));
        Page<Product> products = productRepository.findAll(spec, pageable);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: products) {
            boolean isFavorite = user.getFavoriteProducts().contains(product);
            ProductDto productDto = mapper.toProductDto(product);
            productDto.setInFavorites(isFavorite);
            productDtos.add(productDto);
        }
        return new PaginationResponse(
            products.getTotalElements(),
            size,
            products.getTotalPages(),
            page,
            productDtos
        );
    }

    @Override
    public PaginationResponse findSearch(int page, int size, String keyword, List<Integer> categories, Double minPrice, Double maxPrice, Integer review, String sortField, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasCategory(categories))
                .and(ProductSpecification.hasKeyword(keyword))
                .and(ProductSpecification.minPrice(minPrice))
                .and(ProductSpecification.maxPrice(maxPrice))
                .and(ProductSpecification.review(review));
        Page<Product> products = productRepository.findAll(spec, pageable);
        return new PaginationResponse(
            products.getTotalElements(),
            size,
            products.getTotalPages(),
            page,
            mapper.toProductsDto(products.getContent())
        );
    }

    @Override
    public List<ProductDto> findByCategoryIn(String idsCategories) {
        if(idsCategories.isEmpty()) return mapper.toProductsDto(productRepository.findAll());
        String[] ids = idsCategories.split(",");
        List<CategoryDto> categoriesDto = new ArrayList<>();
        for (String id : ids) {
            CategoryDto categoryDto = categoryService.findById(Long.parseLong(id));
            categoriesDto.add(categoryDto);
        }
        return mapper.toProductsDto(productRepository.findByCategoryIn(mapper.toCategories(categoriesDto)));
    }

    @Override
    public List<RangePriceCountDto> productCountByPriceRange() {
        return productRepository.countProductsByPrice();
    }

    @Override
    public List<ReviewCountDto> productCountByReview() {
        return productRepository.countProductsByReview();
    }



}
