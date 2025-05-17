package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.store.StoreRequestReqDto;
import com.example.demo.dto.store.StoreRequestResDto;
import com.example.demo.entities.StoreRequest;
import com.example.demo.entities.User;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.StoreRequestRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class StoreRequestServiceImpl implements StoreRequestService {

    @Autowired
    private StoreRequestRepository storeRequestRepository;

    @Override
    public StoreRequestResDto create(StoreRequestReqDto dto) {
        try {
            StoreRequest storeRequest = fromDto(dto);
            return toDto(storeRequestRepository.save(storeRequest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StoreRequestResDto updateStatus(Long id, String status) {
        try {
            StoreRequest storeRequest = storeRequestRepository.findById(id).orElse(null);
            if (storeRequest == null) {
                throw new RuntimeException("Store request not found");
            }
            storeRequest.setStoreName(dto.getStoreName());
            storeRequest.setUserId(dto.getUserId());
            storeRequest.setStatus(dto.getStatus());
            storeRequest.setDate(dto.getDate());
            return toDto(storeRequestRepository.save(storeRequest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            storeRequestRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public StoreRequestResDto findById(Long id) {
        try {
            StoreRequest storeRequest = storeRequestRepository.findById(id).orElse(null);
            if (storeRequest == null) {
                throw new RuntimeException("Store request not found");
            }
            return toDto(storeRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<StoreRequestResDto> findAll() {
        try {
            return storeRequestRepository.findAll().stream()
                    .map(this::toDto)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StoreRequest fromDto(StoreRequestReqDto dto) {
        StoreRequest storeRequest = new StoreRequest();;
        storeRequest.setStoreName(dto.getStoreName());
        storeRequest.setUser(dto.getUserId());
        storeRequest.setStatus(dto.getStatus());
        storeRequest.setDate(dto.getDate());
        return storeRequest;
    }

    private StoreRequestResDto toDto(StoreRequest entity) {
        StoreRequestResDto dto = new StoreRequestResDto();
        dto.setId(entity.getId());
        dto.setStoreName(entity.getStoreName());
        dto.setUserId(entity.getUser().getId());
        dto.setStatus(entity.getStatus());
        dto.setDate(entity.getDate());
        return dto;
        // dto.setId(save.getId());
        // dto.setStoreName(save.getStoreName());
        // dto.setUserId(save.getUserId());
        // dto.setStatus(save.getStatus());
        // dto.setDate(save.getDate());
        // return dto;
    }
}
