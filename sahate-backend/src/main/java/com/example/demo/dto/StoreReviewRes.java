package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoreReviewRes {
    private Long id;
    private Long rating;
    private String review;
    private LocalDateTime date;
    private Long storeId;
    private Long customerId;
}
