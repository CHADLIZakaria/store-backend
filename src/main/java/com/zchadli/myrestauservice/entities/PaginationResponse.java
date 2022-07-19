package com.zchadli.myrestauservice.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
    private Long totalElement;
    private int sizePages;
    private int totalPages;
    private int currentPage;
    private List<?> data;
    
}
