package com.khem.appspring.springphoneshop.service;

import java.util.List;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
// import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.model.Brand;

public interface BrandService {
    Brand save(Brand entity);

    Brand getById(Integer id);

    Brand update(Integer id, BrandDTO dto);

    void delete(Integer id);

    List<Brand> findAll();
}
