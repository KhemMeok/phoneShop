package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.khem.appspring.springphoneshop.dto.ProductDTO;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.service.ModelService;

@Mapper(componentModel = "spring",uses = {ModelService.class})
public interface ProductMapper {
    @Mapping(target = "model" , source ="dto.modelId")
    Product toProduct(ProductDTO dto);

    @Mapping(target = "modelId" , source ="model.id")
    ProductDTO toDTO(Product entity);
}
