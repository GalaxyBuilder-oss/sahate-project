package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.store.StoreReqDto;
import com.example.demo.dto.store.StoreResDto;
import com.example.demo.entities.store.Store;
import com.example.demo.repositories.StoreRepository;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public StoreResDto create (StoreReqDto dto) {
        try {
            Store store = fromDto(dto);
            return toDto(storeRepository.save(store));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public StoreResDto update(Long id, StoreReqDto dto) {
        try {
            Store store = storeRepository.findById(id).orElse(null);
            if (store == null) {
                throw new RuntimeException("Store not found");
            }
            store.setStoreName(dto.getStoreName());
            store.setStoreAddress(dto.getStoreAddress());
            store.setDescription(dto.getDescription());
            store.setAccessStatus(dto.getAccessStatus());
            store.setTotalProfit(dto.getTotalProfit());
            return toDto(storeRepository.save(store));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public void delete(Long id){
        try{
            storeRepository.deleteById(id);

        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public StoreResDto findById(Long id) {
        try {
            Store store = storeRepository.findById(id).orElse(null);
            if (store == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store not found");
            }
            return toDto(store);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public List<StoreResDto> findAll() {
        try {
            List<Store> stores = storeRepository.findAll();
            return stores.stream().map(this::toDto).toList();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    private Store fromDto(StoreReqDto dto){
        Store store = new Store();
        store.setStoreName(dto.getStoreName());
        store.setStoreAddress(dto.getStoreAddress());
        store.setDescription(dto.getDescription()); 
        store.setAccessStatus(dto.getAccessStatus());
        store.setTotalProfit(dto.getTotalProfit());
        return store;
    }

    private StoreResDto toDto(Store save){
        StoreResDto dto = new StoreResDto();
        dto.setId(save.getId());
        dto.setStoreName(save.getStoreName()); 
        dto.setStoreAddress(save.getStoreAddress());
        dto.setDescription(save.getDescription());
        dto.setAccessStatus(save.getAccessStatus());
        dto.setTotalProfit(save.getTotalProfit());
        return dto;
    }
    
}