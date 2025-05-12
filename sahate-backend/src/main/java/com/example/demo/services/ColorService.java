package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ColorReqDto;
import com.example.demo.dto.ColorResDto;

public interface ColorService {
    ColorResDto create(ColorReqDto dto);

    ColorResDto update(Long id, ColorReqDto dto);

    void delete(Long id);

    ColorResDto findById(Long id);

    List<ColorResDto> findAll();

}
