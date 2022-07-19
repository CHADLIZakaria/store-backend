package com.zchadli.myrestauservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
    private Long numberProducts;
    private Long numberUsers;
    private Long numberReviews;
    private Long numberCategories;    
}
