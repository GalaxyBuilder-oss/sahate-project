package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.order.OrderReqDto;
import com.example.demo.dto.order.OrderResDto;

public interface OrderService {
    OrderResDto create(OrderReqDto dto);

    OrderResDto update(Long id, OrderReqDto dto);

    void delete(Long id);

    OrderResDto findById(Long id);

    List<OrderResDto> findAll();

}
