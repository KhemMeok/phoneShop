package com.khem.appspring.springphoneshop.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SaleDTO {
	private List<ProductOrderDTO> products;
	
	private LocalDateTime soldDate;
}
