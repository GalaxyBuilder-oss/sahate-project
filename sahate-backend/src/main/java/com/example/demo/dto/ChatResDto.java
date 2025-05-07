package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatResDto {

    private Long id;
    private String message;
    private LocalDateTime sendDateTime;
    private Long sender_id;
    private Long receiver_id;

}
