package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    
}
