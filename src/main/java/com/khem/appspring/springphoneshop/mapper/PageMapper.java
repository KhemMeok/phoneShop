package com.khem.appspring.springphoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.khem.appspring.springphoneshop.dto.PageDTO;
import com.khem.appspring.springphoneshop.dto.PaginationDTO;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);

    @Mapping(target = "pagination",expression = "java(toPaginationDTO(page))")
    @Mapping(target = "list",expression = "java(page.getContent())")
    PageDTO toDTO(Page<?> page);
    

    PaginationDTO toPaginationDTO(Page<?> page);
}
