package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
    
}
