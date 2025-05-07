package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesReportReqDto {
    private Long storeId;    
    private LocalDateTime date;
    private Integer totalSales;
    private Integer totalProfit;
}
