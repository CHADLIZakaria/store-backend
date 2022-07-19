package com.zchadli.myrestauservice.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.Review;
import com.zchadli.myrestauservice.entities.User;

public interface ReviewRespository extends PagingAndSortingRepository<Review, Long> {
   
    public List<Review> findByProductAndIsApprovedTrueOrderByCreatedAtDesc(Product product);  

    public Page<Review> findByDescriptionContaining(String description, Pageable pageable);
    public Page<Review> findByProductAndDescriptionContaining(Product product, String description, Pageable pageable);
    public Page<Review> findByUserAndDescriptionContaining(User user, String description, Pageable pageable);
    public Page<Review> findByProductAndUserAndDescriptionContaining(Product product, User user, String description, Pageable pageable);
    
    public Page<Review> findByDescriptionContainingAndIsApproved(String description, boolean isApproved, Pageable pageable);
    public Page<Review> findByProductAndDescriptionContainingAndIsApproved(Product product, String description, boolean isApproved, Pageable pageable);
    public Page<Review> findByUserAndDescriptionContainingAndIsApproved(User user, String description, boolean isApproved,Pageable pageable);
    public Page<Review> findByProductAndUserAndDescriptionContainingAndIsApproved(Product product, User user, String description, boolean isApproved, Pageable pageable);
    

}
