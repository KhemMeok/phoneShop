package com.khem.appspring.springphoneshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import lombok.Data;

@Entity
@Table(name = "product_import_history")
@Data
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@JoinColumn(name = "date_import")
	private LocalDateTime dateImport;
	
	
	@DecimalMin(value = "0.001")
	@JoinColumn(name = "priceper_uint")
	private BigDecimal pricePerUnit;
	
	@Column(name = "import_unit")
	private Integer importUnit;
	
	
	
}
