package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.model.Brand;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toEntiry(BrandDTO dto);
    BrandDTO toDTO(Brand entity);

}
