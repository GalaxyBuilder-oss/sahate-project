package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping,Long> {
    
}
