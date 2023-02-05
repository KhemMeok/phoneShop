package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.projection.SaleByDate;
import com.khem.appspring.springphoneshop.repository.SaleDetailRepository;
import com.khem.appspring.springphoneshop.service.ReportingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportingServiceIMLP implements ReportingService {
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public List<SaleByDate> getProductBySoldDate(LocalDate soldDate) {
        return saleDetailRepository.findByProduct(soldDate);
    }

}
