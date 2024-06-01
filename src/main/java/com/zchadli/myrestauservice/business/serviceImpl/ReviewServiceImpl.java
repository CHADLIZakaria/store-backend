package com.zchadli.myrestauservice.business.serviceImpl;

import java.util.List;

import com.zchadli.myrestauservice.specification.ReviewSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zchadli.myrestauservice.business.service.ProductService;
import com.zchadli.myrestauservice.business.service.ReviewService;
import com.zchadli.myrestauservice.business.service.UserService;
import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.Review;
import com.zchadli.myrestauservice.mapper.StoreMapper;
import com.zchadli.myrestauservice.repositories.ReviewRespository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRespository reviewRespository;
    private final StoreMapper mapper;
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
    public PaginationResponse findSearch(int page, Integer size, String sort, String sortDirection, String keyword, Long idProduct, String username, Boolean isApproved) {
        Pageable pageable;
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        if(size==null) {
            pageable = Pageable.unpaged();
        }
        else {
            pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        }
        Specification<Review> specification = Specification
                .where(ReviewSpecification.hasProduct(idProduct))
                .and(ReviewSpecification.hasKeyword(keyword))
                .and(ReviewSpecification.isApproved(isApproved))
                .and(ReviewSpecification.hasUser(username));
        Page<Review> reviews = reviewRespository.findAll(specification, pageable);
        return new PaginationResponse(
            reviews.getTotalElements(),
            size==null ? 0 : size ,
            reviews.getTotalPages(),
            page,
            mapper.toReviewsDto(reviews.getContent())
        );
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
