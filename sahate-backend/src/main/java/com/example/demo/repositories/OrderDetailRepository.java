package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.order.OrderDetail;

public interface OrderDetailRepository extends JpaRepository <OrderDetail , Long> {
    
}
