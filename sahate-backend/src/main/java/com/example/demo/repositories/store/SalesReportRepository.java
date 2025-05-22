package com.example.demo.repositories.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.store.SalesReport;

public interface SalesReportRepository extends JpaRepository<SalesReport,Long> {
    
}
