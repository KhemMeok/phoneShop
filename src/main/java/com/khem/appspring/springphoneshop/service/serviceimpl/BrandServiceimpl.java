package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.Util.PageUtil;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.repository.BrandRepository;
import com.khem.appspring.springphoneshop.service.BrandService;
import com.khem.appspring.springphoneshop.specification.BrandFilter;
import com.khem.appspring.springphoneshop.specification.BrandSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceimpl implements BrandService {

    @Autowired
    private final BrandRepository brandRepository;

    @Override
    public Brand save(Brand entity) {
        return brandRepository.save(entity);

    }

    @Override
    public Brand getById(Long id)  {

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
    public Brand update(Long id, Brand source) {
        Brand target = getById(id);
        // brand.setName(dto.getName());
        // source.setId(id);
        // BrandMapper.INSTANCE.update(target, source);
        BeanUtils.copyProperties(source, target, "id");
        return brandRepository.save(target);
    }

    @Override
    public void delete(Long id)  {
        Brand brand = getById(id);
        // brandRepository.delete(brand);
        brand.setActive(false);
        brandRepository.save(brand);
        log.info("brand with id = %d is deleted".formatted(id));
        // log.info("brand with id = %d is deleted", id);

    }

    @Override
    public List<Brand> findAll() {

        // System.out.println("-----------------");
        // boolean existsByName = brandRepository.existsByName("Nokia");
        // System.out.println(existsByName);
        // System.out.println("-----------------");

        return brandRepository.findByActiveTrue();
    }

    @Override
    public Page<Brand> getBrandPage(Map<String, String> param) {
        Pageable pageable = PageUtil.getPageable(param);

        BrandFilter brandFilter = new BrandFilter();
        if (param.containsKey("brandId")) {
            brandFilter.setBrandId(Long.valueOf(MapUtils.getInteger(param, "brandId")));
        }
        if (param.containsKey("brandName")) {
            brandFilter.setBrandName(MapUtils.getString(param, "modelName"));

        }
         
        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);

        Page<Brand> page = brandRepository.findAll(brandSpecification, pageable);

        page.getNumberOfElements();
        return page;
    }

}
