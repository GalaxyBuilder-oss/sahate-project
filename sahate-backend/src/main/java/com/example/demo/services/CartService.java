package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.store.CartReqDto;
import com.example.demo.dto.store.CartResDto;

public interface CartService {
    CartResDto create(CartReqDto dto);
    CartResDto update(Long id, CartReqDto dto);
    void delete(Long id);
    CartResDto findById(Long id);
    List<CartResDto> findAll();
}
