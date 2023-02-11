package com.khem.appspring.springphoneshop.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class SaleByDateDTO {
    private LocalDate soldDate;
    private Long productId;
    private String productName;
    private Long totalUnit;
    private Double amount;
}
