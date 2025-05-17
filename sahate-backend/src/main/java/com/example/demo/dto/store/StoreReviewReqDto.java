package com.example.demo.dto.store;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class StoreReviewReqDto {
    private Long rating;
    private String review;
    private LocalDateTime date;
    private Long storeId;
    private Long customerId;
}
