package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.repository.BrandRepository;
import com.khem.appspring.springphoneshop.service.BrandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BrandServiceimpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand save(Brand entity) {
        return brandRepository.save(entity);

    }

    @Override
    public Brand getById(Integer id)  {

        Brand brandOptinal = brandRepository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, String.format("brand not found for id=%d", id)));
        // if (brandOptinal.isPresent()) {
        // return brandOptinal.get();
        // } else {
        return brandOptinal;

        // throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("brand
        // not found for Id : %d", id));
        // }

    }

    @Override
    public Brand update(Integer id, BrandDTO dto)  {
        Brand brand = getById(id);
        brand.setName(dto.getName());
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Integer id)  {
        Brand brand = getById(id);
        brandRepository.delete(brand);
        log.info("brand with id = %d is deleted".formatted(id));
        // log.info("brand with id = %d is deleted", id);

    }

    @Override
    public List<Brand> findAll() {

        return brandRepository.findAll();
    }

}
