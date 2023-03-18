package com.khem.appspring.springphoneshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.khem.appspring.springphoneshop.model.Brand;

public interface BrandService {
    Brand save(Brand entity);

    Brand getById(Long id);

    Brand update(Long id, Brand  brand);

    void delete(Long id);

    List<Brand> findAll();

    Page<Brand> getBrandPage(Map<String,String> param);
}
