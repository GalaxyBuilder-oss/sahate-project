package com.example.demo.dto.store;

import lombok.Data;

@Data
public class StoreReqDto {
    private String storeName;
    private String storeAddress;
    private String description;
    private String accessStatus;
    private Integer totalProfit;
    private Long userId;
}
