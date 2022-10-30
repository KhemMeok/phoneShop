package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.repository.BrandRepository;
import com.khem.appspring.springphoneshop.service.BrandService;

@Service
public class BrandServiceimpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand save(Brand entity) {
        return brandRepository.save(entity);

    }

    @Override
    public Brand getById(Integer id) {
        
         Optional<Brand> brandOptinal = brandRepository.findById(id);
         if(brandOptinal.isPresent()){
            return brandOptinal.get();
         }else{

            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format("brand not found for Id : %d", id));
         }
         
    }

}
