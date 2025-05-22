package com.example.demo.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.orders.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping,Long> {
    
}
