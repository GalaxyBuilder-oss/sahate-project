package com.example.demo.dto;

import lombok.Data;

@Data
public class UserRes {
    private Long id;
    private String email;
    private String password;
    private boolean status;
}
