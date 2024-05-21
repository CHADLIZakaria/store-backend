package com.zchadli.myrestauservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCountDto {
    private Long id;
    private String name;
    private Long productCount;
}
