package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zchadli.myrestauservice.business.service.CategoryService;
import com.zchadli.myrestauservice.business.service.FileService;
import com.zchadli.myrestauservice.business.service.ProductService;
import com.zchadli.myrestauservice.business.service.SizeService;
import com.zchadli.myrestauservice.dto.CategoryDto;
import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.exceptions.BusinessException;
import com.zchadli.myrestauservice.exceptions.ErrorsMessage;
import com.zchadli.myrestauservice.mapper.RestauMapper;
import com.zchadli.myrestauservice.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final RestauMapper mapper;
    private final FileService fileService;

    @Override
    public ProductDto save(ProductDto productDto, MultipartFile file) {
        // Save File
        String filePath = fileService.save(file);
        productDto.setImagePath(filePath);

        Product product = mapper.toProduct(productDto);
        ProductDto ProductDtoSaved = mapper.toProductDto(productRepository.save(product));
        ProductDtoSaved.setSizes(
            productDto.getSizes().stream().map((size) -> 
                sizeService.save(
                    size, 
                    mapper.toProduct(findById(ProductDtoSaved.getId()))
                ))
            .collect(Collectors.toList())
        );
        return ProductDtoSaved;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.size() == 0) return new ArrayList<ProductDto>();
        return mapper.toProductsDto(productRepository.findAll());
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
    public PaginationResponse findSearch(int page, int size, String keyword, String idsCategories) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = null;
        if(idsCategories.equals("")) {
            products = productRepository.findByTitleContaining(keyword, pageable);
        }
        else {
            String[] ids = idsCategories.split(",");
            List<CategoryDto> categoriesDto = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                CategoryDto categoryDto = categoryService.findById(Long.parseLong(ids[i]));
                categoriesDto.add(categoryDto);
            }
            products = productRepository.findByCategoryIn(mapper.toCategories(categoriesDto), pageable);
        }
        return new PaginationResponse(
            products.getTotalElements(), 
            size, 
            products.getTotalPages(), 
            page, 
            mapper.toProductsDto(products.getContent()));
    }

    @Override
    public List<ProductDto> findByCategoryIn(String idsCategories) {
        if(idsCategories.equals("")) return mapper.toProductsDto(productRepository.findAll());
        String[] ids = idsCategories.split(",");
        List<CategoryDto> categoriesDto = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            CategoryDto categoryDto = categoryService.findById(Long.parseLong(ids[i]));
            categoriesDto.add(categoryDto);
        }
        return mapper.toProductsDto(productRepository.findByCategoryIn(mapper.toCategories(categoriesDto)));
    }

}
