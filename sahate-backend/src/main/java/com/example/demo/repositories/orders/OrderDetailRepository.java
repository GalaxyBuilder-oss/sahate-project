package com.example.demo.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.orders.OrderDetail;

public interface OrderDetailRepository extends JpaRepository <OrderDetail , Long> {
    
}
