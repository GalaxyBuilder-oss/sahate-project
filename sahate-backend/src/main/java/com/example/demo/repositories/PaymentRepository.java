package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.Payment;

public interface PaymentRepository extends JpaRepository <Payment , Long> {
    
}
