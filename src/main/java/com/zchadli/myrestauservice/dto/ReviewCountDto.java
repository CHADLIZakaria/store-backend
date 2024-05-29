package com.zchadli.myrestauservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCountDto {
    private int rating;
    private Long productCount;
}
