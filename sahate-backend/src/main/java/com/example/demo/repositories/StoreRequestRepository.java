package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.StoreRequest;

public interface StoreRequestRepository extends JpaRepository<StoreRequest,Long>{
    
}
