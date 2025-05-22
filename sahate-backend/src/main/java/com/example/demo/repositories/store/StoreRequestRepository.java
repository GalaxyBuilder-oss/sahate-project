package com.example.demo.repositories.store;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.StoreRequest;

public interface StoreRequestRepository extends JpaRepository<StoreRequest,Long>{
    Optional<StoreRequest> findByUserIdAndStatus(Long userId, String status);

}
