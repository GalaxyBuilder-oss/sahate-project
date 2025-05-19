package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.Expedition;

public interface ExpeditionRepository extends JpaRepository <Expedition,Long> {
    
}
