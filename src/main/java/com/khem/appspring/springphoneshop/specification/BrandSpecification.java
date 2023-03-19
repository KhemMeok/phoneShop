package com.khem.appspring.springphoneshop.specification;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.model.Brand_;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandSpecification implements Specification<Brand> {

    private final BrandFilter brandFilter;

    @Override
    @Nullable
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var list = new ArrayList<>();

        if (brandFilter.getBrandId() != null) {
            Predicate filModelId = brand.get(Brand_.ID).in(brandFilter.getBrandId());
            list.add(filModelId);
        }
        if (brandFilter.getBrandName() != null) {
            // Predicate filModelName = model.get("name").in(modelFilter.getModelName());
            Predicate filModelName = cb.like(brand.get(Brand_.NAME), "%" + brandFilter.getBrandName() + "%");
            list.add(filModelName);
        }
        
         
        Predicate[] predicate = list.toArray(Predicate[]::new);
        return cb.and(predicate);
    }

}
