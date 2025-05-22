package com.example.demo.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.Product;

public interface ProductRepository extends JpaRepository <Product , Long> {
    
}
