package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.order.OrderDetailReqDto;
import com.example.demo.dto.order.OrderDetailResDto;

public interface OrderDetailService {
    OrderDetailResDto create(OrderDetailReqDto dto);

    OrderDetailResDto update(Long id, OrderDetailReqDto dto);

    void delete(Long id);

    OrderDetailResDto findById(Long id);

    List<OrderDetailResDto> findAll();
}
