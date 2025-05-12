package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ExpeditionReqDto;
import com.example.demo.dto.ExpeditionResDto;
import com.example.demo.entities.Expedition;
import com.example.demo.repositories.ExpeditionRepository;

@Service
public class ExpeditionServiceImpl implements ExpeditionService {

    @Autowired
    private ExpeditionRepository expeditionRepository;

    @Override
    public ExpeditionResDto create(ExpeditionReqDto dto) {
        try {
            Expedition expedition = fromDto(dto);
            return toDto(expeditionRepository.save(expedition));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ExpeditionResDto toDto(Expedition save) {
        ExpeditionResDto expeditionResDto = new ExpeditionResDto();
        expeditionResDto.setId(save.getId());
        expeditionResDto.setName(save.getName());
        return expeditionResDto;
    }

    private Expedition fromDto(ExpeditionReqDto dto) {
        Expedition expedition = new Expedition();
        expedition.setName(dto.getName());
        return expedition;
    }

    @Override
    public ExpeditionResDto update(Long id, ExpeditionReqDto dto) {
        try {
            Expedition expedition = expeditionRepository.findById(id).orElse(null);
            if (expedition == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Expedition not found");
            }
            expedition.setName(dto.getName());
            return toDto(expeditionRepository.save(expedition));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            expeditionRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is something wrong", e);
        }
    }

    @Override
    public ExpeditionResDto findById(Long id) {
        try {
            Expedition expedition = expeditionRepository.findById(id).orElse(null);
            if (expedition == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Expedition not found");
            }
            return toDto(expedition);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is something wrong", e);
        }
    }

    @Override
    public List<ExpeditionResDto> findAll() {
        return expeditionRepository.findAll().stream().map(this::toDto).toList();
    }
}
