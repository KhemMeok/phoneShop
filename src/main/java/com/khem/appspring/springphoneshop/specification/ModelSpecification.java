package com.khem.appspring.springphoneshop.specification;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.model.Brand_;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.model.Model_;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ModelSpecification implements Specification<Model> {

    private final ModelFilter modelFilter;

    @Override
    @Nullable
    public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {

        var list = new ArrayList<>();

        Join<Model, Brand> brand = model.join("brand");

        if (modelFilter.getModelId() != null) {
            Predicate filModelId = model.get(Model_.ID).in(modelFilter.getModelId());
            list.add(filModelId);
        }
        if (modelFilter.getModelName() != null) {
            // Predicate filModelName = model.get("name").in(modelFilter.getModelName());
            Predicate filModelName = cb.like(model.get(Model_.NAME), "%" + modelFilter.getModelName() + "%");
            list.add(filModelName);
        }
        if (modelFilter.getBrandId() != null) {

            Predicate filBrandID = brand.get("id").in(modelFilter.getBrandId());
            list.add(filBrandID);

        }
        if (modelFilter.getBrandName() != null) {

            Predicate brandName = cb.like(brand.get(Brand_.NAME), "%" + modelFilter.getBrandName() + "%");
            list.add(brandName);

        }
        Predicate[] predicate = list.toArray(Predicate[]::new);
        return cb.and(predicate);
    }

}
