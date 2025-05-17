package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.store.StoreRequestReqDto;
import com.example.demo.dto.store.StoreRequestResDto;
import com.example.demo.entities.StoreRequest;
import com.example.demo.repositories.StoreRequestRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class StoreRequestServiceImpl implements StoreRequestService {
    @Autowired
    private StoreRequestRepository storeRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public StoreRequestResDto create(StoreRequestReqDto dto) {
        try {
            StoreRequest storeRequest = fromDto(dto);
            return toDto(storeRequestRepository.save(storeRequest));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    
    @Override
    public StoreRequestResDto update(Long id, StoreRequestReqDto dto) {
        try {
            StoreRequest storeRequest = storeRequestRepository.findById(id).orElse(null);
            if (storeRequest == null) {
                throw new RuntimeException("Store request not found");
            }
            storeRequest.setStoreName(dto.getStoreName());
            storeRequest.setStatus(dto.getStatus());
            storeRequest.setDate(dto.getDate());
            storeRequest.setUser(userRepository.findById(dto.getUserId()).orElse(null));
            return toDto(storeRequestRepository.save(storeRequest));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            storeRequestRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
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
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public List<StoreRequestResDto> findAll() {
        try {
            return storeRequestRepository.findAll().stream()
                    .map(this::toDto)
                    .toList();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    private StoreRequest fromDto(StoreRequestReqDto dto) {
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(dto.getId());
        storeRequest.setStoreName(dto.getStoreName());
        storeRequest.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        storeRequest.setStatus(dto.getStatus());
        storeRequest.setDate(dto.getDate());
        return storeRequest;
    }
    
    private StoreRequestResDto toDto(StoreRequest save) {
        StoreRequestResDto dto = new StoreRequestResDto();
        dto.setId(save.getId());
        dto.setStoreName(save.getStoreName());
        dto.setUserId(save.getUser().getId());
        dto.setStatus(save.getStatus());
        dto.setDate(save.getDate());
        return dto;
    }
}
