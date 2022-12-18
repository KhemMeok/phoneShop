package com.khem.appspring.springphoneshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDTO {
    
    private Long id;
    private String name;
    private Integer modelId;
    private Short yearMade;
    // private Integer color;
    private BigDecimal importPrice;
    private Double salePrice;
    private LocalDateTime dateImport;
    private String imagePath;
}
