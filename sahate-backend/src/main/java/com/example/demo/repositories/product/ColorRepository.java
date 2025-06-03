package com.example.demo.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
    
}
