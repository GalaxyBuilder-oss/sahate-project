package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
        Cart findByUsername(String username);

}