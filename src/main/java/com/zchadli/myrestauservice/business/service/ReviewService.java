package com.zchadli.myrestauservice.business.service;

import java.util.List;

import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto);
    List<ReviewDto> findAllByProduct(Long idProduct);
    void deleteById(Long idReview);
    ReviewDto toggleApproveReview(Long idReview);
    PaginationResponse findSearch(int page, int size, String keyword, Long idProduct, String username, int isApproved);
    Long getNumberReviews();
}
