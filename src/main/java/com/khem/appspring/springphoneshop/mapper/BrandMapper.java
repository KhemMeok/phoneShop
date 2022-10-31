package com.khem.appspring.springphoneshop.mapper;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.model.Brand;

public class BrandMapper {
    public static Brand toBrand(BrandDTO dto){

        Brand brand = new Brand();
        brand.setName(dto.getName());
        return brand;

    }
    public static BrandDTO toBrandDTO(Brand b){

        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(b.getName());
        return brandDTO;

    }
}
