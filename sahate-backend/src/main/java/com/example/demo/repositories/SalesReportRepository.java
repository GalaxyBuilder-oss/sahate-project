package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.SalesReport;

public interface SalesReportRepository extends JpaRepository<SalesReport,Long> {
    
}
