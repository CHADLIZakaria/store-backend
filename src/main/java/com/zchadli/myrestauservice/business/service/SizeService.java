package com.zchadli.myrestauservice.business.service;

import com.zchadli.myrestauservice.dto.SizeDto;
import com.zchadli.myrestauservice.entities.Product;

public interface SizeService {
    public SizeDto save(SizeDto sizeDto, Product product);
    
}
