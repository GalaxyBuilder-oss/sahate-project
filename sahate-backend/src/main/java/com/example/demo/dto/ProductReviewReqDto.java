package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewReqDto {
    private Long rating;
    private String review;
    private LocalDateTime date;
    private Long productId;
    private Long customerId;
}
