package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;


public interface UserService {
    UserResDto create(UserReqDto dto);
    UserResDto update(Long id, UserReqDto dto);
    void delete(Long id);
    UserResDto findById(Long id);
    List<UserResDto> findAll();
    UserResDto registerAdmin(UserReqDto userReqDto);
    
    
}
