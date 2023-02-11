package com.khem.appspring.springphoneshop.specification;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SaleDetailFilter {
    private LocalDate soldDate;
    private Long productId;
    private Long modelId;
}
