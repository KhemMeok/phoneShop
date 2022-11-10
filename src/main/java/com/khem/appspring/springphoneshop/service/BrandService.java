package com.khem.appspring.springphoneshop.service;

import java.util.List;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.model.Brand;

public interface BrandService {
    Brand save(Brand entity);

    Brand getById(Integer id) throws ApiException;

    Brand update(Integer id, BrandDTO dto) throws ApiException;

    void delete(Integer id) throws ApiException;

    List<Brand> findAll();
}
