package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public PaginationResponse findSearch(int page, Integer size, Long id, String username, String keyword, List<Integer> categories, String categoryName, Double minPrice, Double maxPrice, Integer review, String sortField, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable;
        if(size==null) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortField));
        }
        else {
            pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        }
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasCategory(categories))
                .and(ProductSpecification.hasKeyword(keyword))
                .and(ProductSpecification.minPrice(minPrice))
                .and(ProductSpecification.maxPrice(maxPrice))
                .and(ProductSpecification.review(review))
                .and(ProductSpecification.hasId(id))
                .and(ProductSpecification.hasCategoryName(categoryName));
        Page<Product> products = productRepository.findAll(spec, pageable);
        List<ProductDto> productDtos = null;
        if(username != null && !username.isEmpty()) {
            productDtos = new ArrayList<>();
            RestauUser user = userRepository.findByUsername(username);
            for(Product product: products) {
                boolean isFavorite = user.getFavoriteProducts().contains(product);
                ProductDto productDto = mapper.toProductDto(product);
                productDto.setInFavorites(isFavorite);
                productDtos.add(productDto);
            }
        }
        else {
            productDtos = mapper.toProductsDto(products.getContent());
        }
        return new PaginationResponse(
            products.getTotalElements(),
            size==null ? 0 : size,
            products.getTotalPages(),
            page,
            productDtos
        );
    }
    @Override
    public List<ProductDto> findFavorites(String username) {
        RestauUser user = userRepository.findByUsername(username);
        Specification<Product> spec = Specification.where(ProductSpecification.inFavorite(user.getId()));
        return mapper.toProductsDto(productRepository.findAll(spec)).stream().peek(productDto -> productDto.setInFavorites(true)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findSimilarProducts(Long id, Long idCateory) {
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasNotId(id))
                .and(ProductSpecification.hasCategory(Collections.singletonList(idCateory.intValue())));
        return mapper.toProductsDto(productRepository.findAll(spec));
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
