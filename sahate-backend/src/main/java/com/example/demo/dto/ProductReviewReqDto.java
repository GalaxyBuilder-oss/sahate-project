package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductReviewReqDto {
    private Long rating;
    private String review;
    private LocalDateTime date;
    private Long productId;
    private Long customerId;
}
