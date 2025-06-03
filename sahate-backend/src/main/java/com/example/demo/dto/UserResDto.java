package com.example.demo.dto;

import lombok.Data;

@Data
public class UserResDto {
    private String id;
    private String email;
    private String password;
    private String role;
    private boolean status;
}
