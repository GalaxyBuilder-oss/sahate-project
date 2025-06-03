package com.example.demo.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.Size;

public interface SizeRepository extends JpaRepository<Size,Long> {
    
}
