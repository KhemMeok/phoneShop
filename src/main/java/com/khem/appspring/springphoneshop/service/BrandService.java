package com.khem.appspring.springphoneshop.service;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.model.Brand;

public interface BrandService {
    Brand save(Brand entity);
    Brand getById(Integer id);
    Brand update(Integer id,BrandDTO dto);
}
