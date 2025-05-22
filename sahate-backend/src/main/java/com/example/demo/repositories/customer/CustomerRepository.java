package com.example.demo.repositories.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.customer.Customer;

public interface CustomerRepository extends JpaRepository <Customer , Long>{
    
}
