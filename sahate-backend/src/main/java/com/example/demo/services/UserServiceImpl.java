package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResDto create(UserReqDto dto) {
       
        try {
            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new RuntimeException("Email already registered");
            }
            User user = fromDto(dto);
    
            user = userRepository.save(user);
            return toDto(user);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public UserResDto update(Long id, UserReqDto dto) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            user.setStatus(dto.isStatus());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            return toDto(userRepository.save(user));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public UserResDto findById(Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            return toDto(user);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<UserResDto> findAll() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(this::toDto).toList();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    private User fromDto(UserReqDto dto) {
        User user = new User();
        user.setStatus(dto.isStatus());
        user.setRole(dto.getRole() != null ? dto.getRole() : "USER");
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    private UserResDto toDto(User user) {
        UserResDto dto = new UserResDto();
        dto.setStatus(user.isStatus());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
