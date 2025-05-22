package com.example.demo.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
