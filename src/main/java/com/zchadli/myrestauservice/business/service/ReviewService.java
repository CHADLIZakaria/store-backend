package com.zchadli.myrestauservice.business.service;

import java.util.List;

import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

public interface ReviewService {
    public ReviewDto save(ReviewDto reviewDto);
    public List<ReviewDto> findAllByProduct(Long idProduct);
    public void deleteById(Long idReview);
    public ReviewDto toggleApproveReview(Long idReview);
    public PaginationResponse findSearch(int page, int size, String keyword, Long idProduct, String username, int isApproved);
    public Long getNumberReviews();
    
}
