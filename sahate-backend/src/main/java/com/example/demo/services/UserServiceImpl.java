package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

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
        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    public UserResDto registerAdmin(UserReqDto dto) {
        dto.setRole("ADMIN");
        dto.setStatus(true);
        return create(dto);
    }

    public UserResDto registerToko(UserReqDto dto) {
        dto.setRole("TOKO");
        dto.setStatus(false); // toko default-nya belum tervalidasi
        return create(dto);
    }

    public UserResDto registerPembeli(UserReqDto dto) {
        dto.setRole("PEMBELI");
        dto.setStatus(true); // pembeli langsung aktif
        return create(dto);
    }

    public UserResDto login(String email, String password) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Email not registered"));
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Incorrect password");
            }
            if (!user.isStatus()) {
                throw new RuntimeException("User is not active");
            }
            return toDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Login failed", e);
        }
    }

    @Override
    public UserResDto update(Long id, UserReqDto dto) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            user.setStatus(dto.isStatus());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            return toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    @Override
    public UserResDto findById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            return toDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Error finding user", e);
        }
    }

    @Override
    public List<UserResDto> findAll() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(this::toDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all users", e);
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
