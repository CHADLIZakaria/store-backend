package com.zchadli.myrestauservice.business.serviceImpl;

import org.springframework.stereotype.Service;

import com.zchadli.myrestauservice.business.service.SizeService;
import com.zchadli.myrestauservice.dto.SizeDto;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.Size;
import com.zchadli.myrestauservice.mapper.RestauMapper;
import com.zchadli.myrestauservice.repositories.SizeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final RestauMapper mapper;

    @Override
    public SizeDto save(SizeDto sizeDto, Product product) {
        Size size = mapper.toSize(sizeDto);
        size.setProduct(product);
        return mapper.toSizeDto(sizeRepository.save(size));
    }
    
}
