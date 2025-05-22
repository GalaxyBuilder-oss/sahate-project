package com.example.demo.repositories.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.Payment;

public interface PaymentRepository extends JpaRepository <Payment , Long> {
    
}
