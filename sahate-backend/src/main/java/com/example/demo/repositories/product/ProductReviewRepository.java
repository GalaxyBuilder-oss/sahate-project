package com.example.demo.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.product.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

}
