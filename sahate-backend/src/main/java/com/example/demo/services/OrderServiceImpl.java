package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.order.OrderReqDto;
import com.example.demo.dto.order.OrderResDto;
import com.example.demo.entities.Order;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.StoreRepository;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public OrderResDto create(OrderReqDto dto) {
        try {
            Order order = fromDto(dto);
            return toDto(orderRepository.save(order));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderResDto update(Long id, OrderReqDto dto) {
        try {
            Order order = orderRepository.findById(id).orElse(null);
            if (order == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found");
            }
            order.setPaymentMethod(dto.getPaymentMethod());
            order.setDeliveryStatus(dto.getDeliveryStatus());
            order.setPurchaseDate(dto.getPurchaseDate());
            order.setTotalPrice(dto.getTotalPrice());
           order.setBuyer(customerRepository.findById(dto.getBuyerId()).orElse(null));
           order.setStore(storeRepository.findById(dto.getStoreId()).orElse(null));
            return toDto(orderRepository.save(order));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {
        try{
            orderRepository.deleteById(id);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }

    }

    @Override
    public OrderResDto findById(Long id) {
        try{
            Order order = orderRepository.findById(id).orElse(null);
            if(order == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found"); 
            }
            return toDto(order);
        }
        catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<OrderResDto> findAll() {
        return orderRepository.findAll().stream().map(this::toDto).toList();
    }

    private Order fromDto(OrderReqDto dto) {
        Order order = new Order();
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setDeliveryStatus(dto.getDeliveryStatus());
        order.setPurchaseDate(dto.getPurchaseDate());
        order.setTotalPrice(dto.getTotalPrice());
//        order.setBuyer(customerRepository.findById(dto.getBuyerId()).orElse(null));
//        order.setStore(storeRepository.findById(dto.getStoreId()).orElse(null));
        return order;
    }

    private OrderResDto toDto(Order save) {
        OrderResDto dto = new OrderResDto();
        dto.setId(save.getId());
        dto.setPaymentMethod(save.getPaymentMethod());
        dto.setDeliveryStatus(save.getDeliveryStatus());
        dto.setPurchaseDate(save.getPurchaseDate());
        dto.setTotalPrice(save.getTotalPrice());
//        dto.setBuyerId(save.getBuyer().getId());
//        dto.setStoreId(save.getStore().getId());
        return dto;
    }

}
