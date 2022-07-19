package com.zchadli.myrestauservice.business.serviceImpl;

import org.springframework.stereotype.Service;

import com.zchadli.myrestauservice.business.service.CategoryService;
import com.zchadli.myrestauservice.business.service.ProductService;
import com.zchadli.myrestauservice.business.service.ReviewService;
import com.zchadli.myrestauservice.business.service.StatisticService;
import com.zchadli.myrestauservice.business.service.UserService;
import com.zchadli.myrestauservice.entities.Stats;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;
    private final ProductService productService;

    @Override
    public Stats getStatistics() {
       return new Stats(
            productService.getNumberPoruducts(),  
            userService.getNumberUsers(), 
            reviewService.getNumberReviews(), 
            categoryService.getNumberCategories());
    }
    
}
