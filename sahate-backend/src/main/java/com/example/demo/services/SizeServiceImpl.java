package com.example.demo.services;

import com.example.demo.dto.SizeReqDto;
import com.example.demo.dto.SizeResDto;
import com.example.demo.entities.Size;
import com.example.demo.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public SizeResDto create(SizeReqDto dto) {
        Size size = new Size();
        size.setSize(dto.getSize());
        return toDto(sizeRepository.save(size));
    }

    @Override
    public SizeResDto update(Long id, SizeReqDto dto) {
        Size size = sizeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Size not found"));

        size.setSize(dto.getSize());
        return toDto(sizeRepository.save(size));
    }

    @Override
    public void delete(Long id) {
        if (!sizeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Size not found");
        }
        sizeRepository.deleteById(id);
    }

    @Override
    public SizeResDto findById(Long id) {
        Size size = sizeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Size not found"));
        return toDto(size);
    }

    @Override
    public List<SizeResDto> findAll() {
        return sizeRepository.findAll().stream().map(this::toDto).toList();
    }

    private SizeResDto toDto(Size size) {
        SizeResDto dto = new SizeResDto();
        dto.setId(size.getId());
        dto.setSize(size.getSize());
        return dto;
    }
}
