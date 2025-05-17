package com.example.demo.services;

import com.example.demo.dto.order.ShippingReqDto;
import com.example.demo.dto.order.ShippingResDto;

import java.util.List;

public interface ShippingService {
    ShippingResDto create(ShippingReqDto dto);
    ShippingResDto update(Long id, ShippingReqDto dto);
    void delete(Long id);
    ShippingResDto findById(Long id);
    List<ShippingResDto> findAll();
}
