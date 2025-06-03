package com.example.demo.repositories.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.StoreReview;

public interface StoreReviewRepository extends JpaRepository<StoreReview,Long>{
    
}
