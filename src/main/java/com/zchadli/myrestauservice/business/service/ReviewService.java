package com.zchadli.myrestauservice.business.service;

import java.util.List;

import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto);
    List<ReviewDto> findAllByProduct(Long idProduct);
    void deleteById(Long idReview);
    ReviewDto toggleApproveReview(Long idReview);
    PaginationResponse findSearch(int page, Integer size, String sort, String direction, String keyword, Long idProduct, String username, Boolean isApproved);
    Long getNumberReviews();
}
