package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ProductReqDto;
import com.example.demo.dto.ProductResDto;

public interface ProductService {
    ProductResDto create(ProductReqDto dto);

    ProductResDto update(Long id, ProductReqDto dto);

    void delete(Long id);

    ProductResDto findById(Long id);

    List<ProductResDto> findAll();
}
