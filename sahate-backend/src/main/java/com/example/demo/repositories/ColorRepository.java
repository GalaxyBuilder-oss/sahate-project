package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
    
}
