package com.khem.appspring.springphoneshop.model;

import java.math.BigDecimal;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sale_details")

public class SaleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private BigDecimal amount;
	
	private Integer unit;
	
}
