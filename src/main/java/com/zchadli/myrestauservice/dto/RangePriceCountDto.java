package com.zchadli.myrestauservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangePriceCountDto {
    private Long id;
    private Double minPrice;
    private Double maxPrice;
    private Long productCount;
}
