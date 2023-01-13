package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.khem.appspring.springphoneshop.dto.ImportDTO;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.model.ProductImportHistory;
import com.khem.appspring.springphoneshop.service.ProductService;

@Mapper(componentModel = "spring",uses = {ProductService.class})
public interface ProductImportHistoryMapper {
	
	ProductImportHistoryMapper iNSTANCE= Mappers.getMapper(ProductImportHistoryMapper.class);
	@Mapping(target = "product",source = "product")
	@Mapping(target = "id" , ignore = true )
	ProductImportHistory toEntity(ImportDTO importDTO, Product product);
	
	
}
