package com.example.demo.services;

import com.example.demo.dto.CartReqDto;
import com.example.demo.dto.CartResDto;

import java.util.List;

public interface CartService {
    CartResDto create(CartReqDto dto);
    CartResDto update(Long id, CartReqDto dto);
    void delete(Long id);
    CartResDto findById(Long id);
    List<CartResDto> findAll();
}
