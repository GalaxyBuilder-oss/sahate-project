package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.product.ColorReqDto;
import com.example.demo.dto.product.ColorResDto;
import com.example.demo.entities.Color;
import com.example.demo.repositories.ColorRepository;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public ColorResDto create(ColorReqDto dto) {

        try {
            Color color = fromDto(dto);
            return toDto(colorRepository.save(color));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public ColorResDto update(Long id, ColorReqDto dto) {

        try {
            Color color = colorRepository.findById(id).orElse(null);
            if (color == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not found");
            }
            color.setColor(dto.getColor());
            color.setProductImage(dto.getProductImage());
            return toDto(colorRepository.save(color));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {

        try {
            colorRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public ColorResDto findById(Long id) {

        try {
            Color color = colorRepository.findById(id).orElse(null);
            if (color == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not found");
            }
            return toDto(color);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<ColorResDto> findAll() {

        return colorRepository.findAll().stream().map(this::toDto).toList();
    }

    private Color fromDto(ColorReqDto dto) {
        Color color = new Color();
        color.setColor(dto.getColor());
        color.setProductImage(dto.getProductImage());
        return color;
    }

    private ColorResDto toDto(Color save) {
        ColorResDto dto = new ColorResDto();
        dto.setId(save.getId());
        dto.setColor(save.getColor());
        dto.setProductImage(save.getProductImage());
        return dto;
    }

}
