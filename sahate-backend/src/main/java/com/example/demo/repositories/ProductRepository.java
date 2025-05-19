package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.Product;

public interface ProductRepository extends JpaRepository <Product , Long> {
    
}
