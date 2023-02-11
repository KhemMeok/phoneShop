package com.khem.appspring.springphoneshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.SaleByDateDTO;
import com.khem.appspring.springphoneshop.projection.SaleByDate;
import com.khem.appspring.springphoneshop.service.ReportingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportingService reportingService;
    @GetMapping("/dailyProduct/{soldDate}")
    public ResponseEntity<?> getProductSoldDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate soldDate) {
         List<SaleByDate> productBySoldDate = reportingService.getProductBySoldDate(soldDate);
         return ResponseEntity.ok(productBySoldDate);
    }
    @GetMapping("/dailyProduct/v2/{soldDate}")
    public ResponseEntity<?> getProductSoldDateV2(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate soldDate) {
         List<SaleByDateDTO> productBySoldDate = reportingService.getProductSoldByDateV2(soldDate);
         return ResponseEntity.ok(productBySoldDate);
    }
}
