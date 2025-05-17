package com.example.demo.services;

import com.example.demo.dto.StoreReqDto;
import com.example.demo.dto.StoreResDto;
import com.example.demo.entities.Store;
import com.example.demo.entities.User;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public StoreResDto create(StoreReqDto dto) {
        try {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

            // Validasi: hanya user dengan role TOKO yang bisa buat store
            if (!"TOKO".equalsIgnoreCase(user.getRole())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only TOKO role can create a store");
            }

            Store store = fromDto(dto);
            store.setUser(user); // relasi user-store
            return toDto(storeRepository.save(store));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating store", e);
        }
    }

    @Override
    public StoreResDto update(Long id, StoreReqDto dto) {
        try {
            Store store = storeRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));

            store.setStoreName(dto.getStoreName());
            store.setStoreAddress(dto.getStoreAddress());
            store.setDescription(dto.getDescription());
            store.setAccessStatus(dto.getAccessStatus());
            store.setTotalProfit(dto.getTotalProfit());
            return toDto(storeRepository.save(store));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating store", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            storeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting store", e);
        }
    }

    @Override
    public StoreResDto findById(Long id) {
        try {
            Store store = storeRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
            return toDto(store);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding store", e);
        }
    }

    @Override
    public List<StoreResDto> findAll() {
        try {
            List<Store> stores = storeRepository.findAll();
            return stores.stream().map(this::toDto).toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding stores", e);
        }
    }

    private Store fromDto(StoreReqDto dto) {
        Store store = new Store();
        store.setStoreName(dto.getStoreName());
        store.setStoreAddress(dto.getStoreAddress());
        store.setDescription(dto.getDescription());
        store.setAccessStatus(dto.getAccessStatus());
        store.setTotalProfit(dto.getTotalProfit());
        return store;
    }

    private StoreResDto toDto(Store save) {
        StoreResDto dto = new StoreResDto();
        dto.setId(save.getId());
        dto.setStoreName(save.getStoreName());
        dto.setStoreAddress(save.getStoreAddress());
        dto.setDescription(save.getDescription());
        dto.setAccessStatus(save.getAccessStatus());
        dto.setTotalProfit(save.getTotalProfit());
        dto.setUserId(save.getUser().getId()); // tampilkan ID user
        return dto;
    }
}
