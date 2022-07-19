package com.zchadli.myrestauservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private int rating;
    private String description;
    private ProductDto product;
    private UserDto user;
    private LocalDateTime createdAt;
    private boolean isApproved;
}
