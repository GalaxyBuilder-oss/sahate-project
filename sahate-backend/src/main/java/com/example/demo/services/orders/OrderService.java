package com.example.demo.services.orders;

import java.util.List;

import com.example.demo.dto.orders.OrderReqDto;
import com.example.demo.dto.orders.OrderResDto;

public interface OrderService {
    OrderResDto create(OrderReqDto dto);

    OrderResDto update(Long id, OrderReqDto dto);

    void delete(Long id);

    OrderResDto findById(Long id);

    List<OrderResDto> findAll();

}
