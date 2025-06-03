package com.example.demo.services.orders;

import java.util.List;

import com.example.demo.dto.orders.ShippingReqDto;
import com.example.demo.dto.orders.ShippingResDto;

public interface ShippingService {
    ShippingResDto create(ShippingReqDto dto);
    ShippingResDto update(Long id, ShippingReqDto dto);
    void delete(Long id);
    ShippingResDto findById(Long id);
    List<ShippingResDto> findAll();
}
