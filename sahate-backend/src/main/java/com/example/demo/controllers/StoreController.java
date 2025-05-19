package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.store.StoreReqDto;
import com.example.demo.dto.store.StoreResDto;
import com.example.demo.services.StoreService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/stores")
public class StoreController
{

    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<StoreResDto> createStore(@RequestBody StoreReqDto dto) {
        StoreResDto createdStore = storeService.create(dto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<StoreResDto> updateStore(@PathVariable Long id, @RequestBody StoreReqDto dto) {
        StoreResDto updatedStore = storeService.update(id, dto);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<StoreResDto> getStoreById(@PathVariable Long id) {
        StoreResDto store = storeService.findById(id);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreResDto>> getAllStores() {
        List<StoreResDto> stores = storeService.findAll();
        return ResponseEntity.ok(stores);
    }
}

