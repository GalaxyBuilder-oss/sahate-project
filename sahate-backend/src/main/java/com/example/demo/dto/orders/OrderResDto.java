package com.example.demo.dto.orders;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResDto {

    private Long id;
    private String paymentMethod;
    private String deliveryStatus;
    private LocalDateTime purchaseDate;
    private Integer totalPrice;
    private Long buyerId;
    private Long storeId;

    // Field-field tambahan untuk status dan alur pembayaran
    private String paymentStatus; // e.g., "PENDING", "SUCCESS", "FAILED"
    private String orderStatus;   // e.g., "PENDING", "PROCESSING", "COMPLETED"
    private String redirectUrl;   // URL pembayaran dari Midtrans
}