package com.khem.appspring.springphoneshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ImportDTO {
	private Integer importUnit;
	private BigDecimal pricePerUnit;
	private LocalDateTime dateImport;
}
