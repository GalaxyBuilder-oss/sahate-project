package com.example.demo.dto.customer;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerResDto {
    private Long id;
    private String name;
    private String placeBirth;
    private LocalDate timeBirth;
    private String address;
    private String numberPhone;
    private Long userId;
}
