package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatResDto {

    private Long id;
    private String message;
    private LocalDateTime sendDateTime;
    private Long senderId;
    private Long receiverId;

}
