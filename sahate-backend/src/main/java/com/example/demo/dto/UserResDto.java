package com.example.demo.dto;

import lombok.Data;

@Data
public class UserResDto {
    private Long id;
    private String email;
    private String password;
    private String role;
    private boolean status;
}
