package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.model.Model;

@Mapper
public interface ModelMapper {
    
    ModelMapper INSTANC = Mappers.getMapper(ModelMapper.class);

    Model toModel(ModelDTO dto );

}
