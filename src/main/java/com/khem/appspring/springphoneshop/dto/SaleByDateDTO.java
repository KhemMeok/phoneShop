package com.khem.appspring.springphoneshop.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class SaleByDateDTO {
    private LocalDate soldDate;
    private Long productid;
    private String productName;
    private Long totalUnit;
    private Double amount;
}
