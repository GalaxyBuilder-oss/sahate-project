package com.example.demo.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    
}
