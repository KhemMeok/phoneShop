package com.khem.appspring.springphoneshop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.SaleByDateDTO;
import com.khem.appspring.springphoneshop.projection.SaleByDate;
@Service
public interface ReportingService {
    List<SaleByDate> getProductBySoldDate(LocalDate soldDate);
    List<SaleByDateDTO> getProductSoldByDateV2(LocalDate soldDate);

}
