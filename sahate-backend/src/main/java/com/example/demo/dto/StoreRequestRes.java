package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class StoreRequestRes {
    private Long id;
    private Long userId; // Fk Users
    private String storeName;
    private String status;
    private LocalDateTime date;
}
