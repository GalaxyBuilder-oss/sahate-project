package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StoreRequestReqDto;
import com.example.demo.dto.StoreRequestResDto;
import com.example.demo.entities.StoreRequest;
import com.example.demo.repositories.StoreRequestRepository;

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
    public StoreRequestResDto update(Long id, StoreRequestReqDto dto) {
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
        storeRequest.setId(dto.getId());
        storeRequest.setStoreName(dto.getStoreName());
        storeRequest.setUserId(dto.getUserId());
        storeRequest.setStatus(dto.getStatus());
        storeRequest.setDate(dto.getDate());
        return storeRequest;
    }
    
    private StoreRequestResDto toDto(StoreRequest save) {
        StoreRequestResDto dto = new StoreRequestResDto();
        dto.setId(save.getId());
        dto.setStoreName(save.getStoreName());
        dto.setUserId(save.getUserId());
        dto.setStatus(save.getStatus());
        dto.setDate(save.getDate());
        return dto;
    }
}
