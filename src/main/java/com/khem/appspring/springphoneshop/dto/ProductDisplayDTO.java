package com.khem.appspring.springphoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDisplayDTO {
	private Long id;
	private String name;
	private String model;
	private String color;
	private BigDecimal salePrice;
	
}
