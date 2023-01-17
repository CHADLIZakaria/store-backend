package com.zchadli.myrestauservice.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imagePath;
    private CategoryDto category;
    private List<SizeDto> sizes = new ArrayList<SizeDto>();
    //private Set<ReviewDto> reviews = new HashSet<ReviewDto>();
}
