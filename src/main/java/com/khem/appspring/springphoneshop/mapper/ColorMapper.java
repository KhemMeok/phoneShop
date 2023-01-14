package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.khem.appspring.springphoneshop.dto.ColorDTO;
import com.khem.appspring.springphoneshop.model.Color;
import com.khem.appspring.springphoneshop.service.ColorService;

@Mapper(componentModel = "spring",uses = {ColorService.class})
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
    Color toColor(ColorDTO colorDTO);
    ColorDTO toDTO(Color entity);
}
