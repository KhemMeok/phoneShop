package com.khem.appspring.springphoneshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.projection.SaleByDate;
import com.khem.appspring.springphoneshop.service.ReportingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportingService reportingService;
    @GetMapping("/dailyProduct/{soldDate}")
    public ResponseEntity<?> getProductSoldDate(@PathVariable LocalDate soldDate){
         List<SaleByDate> productBySoldDate = reportingService.getProductBySoldDate(soldDate);
         return ResponseEntity.ok(productBySoldDate);
    }
}
