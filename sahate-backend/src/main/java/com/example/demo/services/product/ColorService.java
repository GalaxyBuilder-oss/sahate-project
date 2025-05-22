package com.example.demo.services.product;

import java.util.List;

import com.example.demo.dto.product.ColorReqDto;
import com.example.demo.dto.product.ColorResDto;

public interface ColorService {
    ColorResDto create(ColorReqDto dto);

    ColorResDto update(Long id, ColorReqDto dto);

    void delete(Long id);

    ColorResDto findById(Long id);

    List<ColorResDto> findAll();

}
