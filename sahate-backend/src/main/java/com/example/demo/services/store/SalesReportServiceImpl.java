package com.example.demo.services.store;

import com.example.demo.dto.store.SalesReportReqDto;
import com.example.demo.dto.store.SalesReportResDto;
import com.example.demo.entities.store.SalesReport;
import com.example.demo.entities.store.Store;
import com.example.demo.repositories.store.SalesReportRepository;
import com.example.demo.repositories.store.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalesReportServiceImpl implements SalesReportService {

    @Autowired
    private SalesReportRepository salesReportRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public SalesReportResDto create(SalesReportReqDto dto) {
        SalesReport report = fromDto(dto);
        return toDto(salesReportRepository.save(report));
    }

    @Override
    public SalesReportResDto update(Long id, SalesReportReqDto dto) {
        SalesReport existing = salesReportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales report not found"));

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store not found"));

        existing.setStore(store);
        existing.setDate(dto.getDate());
        existing.setTotalSales(dto.getTotalSales());
        existing.setTotalProfit(dto.getTotalProfit());

        return toDto(salesReportRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!salesReportRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales report not found");
        }
        salesReportRepository.deleteById(id);
    }

    @Override
    public SalesReportResDto findById(Long id) {
        SalesReport report = salesReportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales report not found"));
        return toDto(report);
    }

    @Override
    public List<SalesReportResDto> findAll() {
        return salesReportRepository.findAll().stream().map(this::toDto).toList();
    }

    // Helper Methods
    private SalesReport fromDto(SalesReportReqDto dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store not found"));

        SalesReport report = new SalesReport();
        report.setStore(store);
        report.setDate(dto.getDate());
        report.setTotalSales(dto.getTotalSales());
        report.setTotalProfit(dto.getTotalProfit());

        return report;
    }

    private SalesReportResDto toDto(SalesReport report) {
        SalesReportResDto dto = new SalesReportResDto();
        dto.setId(report.getId());
        dto.setStoreId(report.getStore().getId());
        dto.setDate(report.getDate());
        dto.setTotalSales(report.getTotalSales());
        dto.setTotalProfit(report.getTotalProfit());
        return dto;
    }
}
