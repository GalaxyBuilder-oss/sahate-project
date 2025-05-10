package com.example.demo.services;


import java.util.List;

import com.example.demo.dto.ChatReqDto;
import com.example.demo.dto.ChatResDto;

public interface ChatService {
    ChatResDto create(ChatReqDto dto);
    ChatResDto update(Long id, ChatReqDto dto);
    void delete(Long id);
    ChatResDto findById(Long id);
    List<ChatResDto> findAll();
}
