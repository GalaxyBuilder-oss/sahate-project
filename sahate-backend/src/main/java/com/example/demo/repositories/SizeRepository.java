package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Size;

public interface SizeRepository extends JpaRepository<Size,Long> {
    
}
