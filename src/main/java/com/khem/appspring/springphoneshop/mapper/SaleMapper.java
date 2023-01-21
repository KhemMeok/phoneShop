package com.khem.appspring.springphoneshop.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.khem.appspring.springphoneshop.dto.ProductOrderDTO;
import com.khem.appspring.springphoneshop.dto.SaleDTO;
import com.khem.appspring.springphoneshop.model.Sale;
import com.khem.appspring.springphoneshop.model.SaleDetail;
import com.khem.appspring.springphoneshop.service.ProductService;


@Mapper(componentModel = "spring",uses = {ProductService.class})
public interface SaleMapper {
	Sale toSale(SaleDTO dto);
	@Mapping(target = "sale", source = "sale")
	@Mapping(target = "product",source = "dto.productId")
	SaleDetail toSaleDetail(ProductOrderDTO dto, Sale sale ,BigDecimal amount);
}
