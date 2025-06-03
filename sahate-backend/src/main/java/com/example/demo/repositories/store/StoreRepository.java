package com.example.demo.repositories.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
    
}
