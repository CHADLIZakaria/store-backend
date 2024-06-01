package com.zchadli.myrestauservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.Review;

public interface ReviewRespository extends PagingAndSortingRepository<Review, Long>, JpaSpecificationExecutor<Review> {
   
    public List<Review> findByProductAndIsApprovedTrueOrderByCreatedAtDesc(Product product);

}
