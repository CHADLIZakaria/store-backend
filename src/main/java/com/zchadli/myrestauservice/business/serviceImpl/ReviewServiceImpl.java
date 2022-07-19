package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zchadli.myrestauservice.business.service.ProductService;
import com.zchadli.myrestauservice.business.service.ReviewService;
import com.zchadli.myrestauservice.business.service.UserService;
import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.dto.UserDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.Review;
import com.zchadli.myrestauservice.entities.User;
import com.zchadli.myrestauservice.mapper.RestauMapper;
import com.zchadli.myrestauservice.repositories.ReviewRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRespository reviewRespository;
    private final RestauMapper mapper;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Review review = mapper.toReview(reviewDto); 
        review.setApproved(true);
        return mapper.toReviewDto(reviewRespository.save(review));
    }

    @Override
    public List<ReviewDto> findAllByProduct(Long idProduct) {
        Product product = mapper.toProduct(productService.findById(idProduct));
        return mapper.toReviewsDto(reviewRespository.findByProductAndIsApprovedTrueOrderByCreatedAtDesc(product));
    }   

    public PaginationResponse findSearch(int page, int size, String keyword, Long idProduct, String username, int isApproved) {
        Pageable pageable = PageRequest.of(page, size,  Sort.unsorted());
        Page<Review> reviews = null;
        User user = null;
        Product product = null; 
        if(!username.equals("")) {
            UserDto userDto = userService.findByUsername(username);
            user = mapper.toUser(userDto);
        }
        if(idProduct != null) {
            ProductDto productDto = productService.findById(idProduct);
            product = mapper.toProduct(productDto);
        }


        if(isApproved == -1) {
            if(username.equals("")) {
                if(idProduct == null ) {
                    reviews = reviewRespository.findByDescriptionContaining(keyword, pageable);
                }
                else {
                    reviews = reviewRespository.findByProductAndDescriptionContaining(product, keyword, pageable);
                }
            }
            else if(!username.equals("")) {
                if(idProduct == null) {
                    reviews = reviewRespository.findByUserAndDescriptionContaining(user, keyword, pageable);
                }
                else {
                    reviews = reviewRespository.findByProductAndUserAndDescriptionContaining(product, user, keyword, pageable);
                }
            }    
        }
        else {
            boolean approved = isApproved==1 ? true : false;
            if(username.equals("")) {
                if(idProduct == null ) {
                    reviews = reviewRespository.findByDescriptionContainingAndIsApproved(keyword, approved, pageable);
                }
                else {
                    reviews = reviewRespository.findByProductAndDescriptionContainingAndIsApproved(product, keyword, approved, pageable);
                }
            }
            else if(!username.equals("")) {
                if(idProduct == null) {
                    reviews = reviewRespository.findByUserAndDescriptionContainingAndIsApproved(user, keyword, approved, pageable);
                }
                else {
                    reviews = reviewRespository.findByProductAndUserAndDescriptionContainingAndIsApproved(product, user, keyword, approved, pageable);
                }
            }    
        }
        return new PaginationResponse(reviews.getTotalElements(), size, reviews.getTotalPages(), page, mapper.toReviewsDto(reviews.getContent()));
    }

    @Override
    public void deleteById(Long idReview) {
       reviewRespository.deleteById(idReview);
    }

    @Override
    public ReviewDto toggleApproveReview(Long idReview) {
        Review review = reviewRespository.findById(idReview).get();
        review.setApproved(!review.isApproved());
        return mapper.toReviewDto(reviewRespository.save(review));
    }

    @Override
    public Long getNumberReviews() {
        return reviewRespository.count();
    }
    
}
