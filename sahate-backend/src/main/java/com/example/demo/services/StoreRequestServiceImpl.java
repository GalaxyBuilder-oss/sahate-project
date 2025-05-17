package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.StoreRequestReqDto;
import com.example.demo.dto.StoreRequestResDto;
import com.example.demo.entities.Store;
import com.example.demo.entities.StoreRequest;
import com.example.demo.entities.User;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.StoreRequestRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class StoreRequestServiceImpl implements StoreRequestService {

    @Autowired
    private StoreRequestRepository storeRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public StoreRequestResDto create(StoreRequestReqDto dto) {
        try {
            // Validasi user ada
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            if (!"TOKO".equalsIgnoreCase(user.getRole())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only TOKO users can request to open a store");
            }

            // Optional: hanya bisa 1 permintaan aktif per user
            Optional<StoreRequest> existing = storeRequestRepository.findByUserIdAndStatus(dto.getUserId(), "PENDING");
            if (existing.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You already have a pending request");
            }

            StoreRequest request = fromDto(dto);
            request.setStatus("PENDING");
            return toDto(storeRequestRepository.save(request));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating store request", e);
        }
    }

    @Override
    public StoreRequestResDto updateStatus(Long id, String status) {
        try {
            StoreRequest request = storeRequestRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));

            request.setStatus(status.toUpperCase());

            // Jika disetujui, otomatis buat Store
            if ("APPROVED".equalsIgnoreCase(status)) {
                Store store = new Store();
                store.setStoreName(request.getStoreName());
                store.setUser(userRepository.findById(request.getUserId()).orElseThrow());
                store.setStoreAddress("Default Address");
                store.setAccessStatus("OPEN");
                store.setDescription("Store created from approved request");
                storeRepository.save(store);
            }

            return toDto(storeRequestRepository.save(request));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating request status", e);
        }
    }

    @Override
    public List<StoreRequestResDto> findAll() {
        return storeRequestRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public StoreRequestResDto findById(Long id) {
        StoreRequest req = storeRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
        return toDto(req);
    }

    @Override
    public void delete(Long id) {
        storeRequestRepository.deleteById(id);
    }

    private StoreRequest fromDto(StoreRequestReqDto dto) {
        StoreRequest request = new StoreRequest();
        request.setStoreName(dto.getStoreName());
        request.setUserId(dto.getUserId());
        request.setDate(LocalDateTime.now());
        return request;
    }

    private StoreRequestResDto toDto(StoreRequest entity) {
        StoreRequestResDto dto = new StoreRequestResDto();
        dto.setId(entity.getId());
        dto.setStoreName(entity.getStoreName());
        dto.setUserId(entity.getUserId());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
