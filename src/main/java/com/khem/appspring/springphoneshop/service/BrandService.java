package com.khem.appspring.springphoneshop.service;

import java.util.List;

 
import com.khem.appspring.springphoneshop.model.Brand;

public interface BrandService {
    Brand save(Brand entity);

    Brand getById(Integer id);

    Brand update(Integer id, Brand  brand);

    void delete(Integer id);

    List<Brand> findAll();
}
