package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.service.BrandService;

@Mapper(componentModel = "spring" ,uses={BrandService.class})
public interface ModelEntityMapper {

    ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);

     @Mapping(target = "brand" ,source = "dto.brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toDTO(Model entity);

    // Brand toBrand(Integer brandId);

}
