package com.example.demo.repositories.store;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}