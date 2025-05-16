package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.order.OrderDetailReqDto;
import com.example.demo.dto.order.OrderDetailResDto;
import com.example.demo.entities.OrderDetail;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailResDto create(OrderDetailReqDto dto) {
        try {
            OrderDetail detail = fromDto(dto);
            return toDto(orderDetailRepository.save(detail));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public OrderDetailResDto update(Long id, OrderDetailReqDto dto) {
        try {
            OrderDetail detail = orderDetailRepository.findById(id).orElse(null);
            if (detail == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found");
            }
            detail.setQty(dto.getQty());
            detail.setUnitPrice(dto.getUnitPrice());
            detail.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));
            detail.setProductDetail(productDetailRepository.findById(dto.getProductDetailId()).orElse(null));
            return toDto(orderDetailRepository.save(detail));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }

    }

    @Override
    public void delete(Long id) {
        try {
            orderDetailRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public OrderDetailResDto findById(Long id) {

        try {
            OrderDetail detail = orderDetailRepository.findById(id).orElse(null);
            if (detail == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order detail not found");
            }
            return toDto(detail);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<OrderDetailResDto> findAll() {

        return orderDetailRepository.findAll().stream().map(this::toDto).toList();
    }

    private OrderDetail fromDto(OrderDetailReqDto dto) {
        OrderDetail detail = new OrderDetail();
        detail.setQty(dto.getQty());
        detail.setUnitPrice(dto.getUnitPrice());
        detail.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));
        detail.setProductDetail(productDetailRepository.findById(dto.getProductDetailId()).orElse(null));
        return detail;
    }

    private OrderDetailResDto toDto(OrderDetail save) {
        OrderDetailResDto dto = new OrderDetailResDto();
        dto.setId(save.getId());
        dto.setQty(save.getQty());
        dto.setUnitPrice(save.getUnitPrice());
        dto.setOrderId(save.getOrder().getId());
        dto.setProductDetailId(save.getProductDetail().getId());
        return dto;
    }
}
