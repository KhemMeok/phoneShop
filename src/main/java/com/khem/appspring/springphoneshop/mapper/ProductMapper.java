package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.khem.appspring.springphoneshop.dto.ProductDTO;
import com.khem.appspring.springphoneshop.dto.ProductDTO2;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.service.ColorService;
import com.khem.appspring.springphoneshop.service.ModelService;

@Mapper(componentModel = "spring",uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model" , source ="dto.modelId")
    @Mapping(target = "color",source = "dto.colorId")
    Product toProduct(ProductDTO dto);

    @Mapping(target = "modelId" , source ="model.id")
    @Mapping(target = "colorId",source = "color.id")
    ProductDTO2 toDTO(Product entity);
}
