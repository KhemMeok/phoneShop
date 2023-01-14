package com.khem.appspring.springphoneshop.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.khem.appspring.springphoneshop.model.Product;

public class ProductSpacifition implements Specification<Product> {

    @Override
    @Nullable
    public Predicate toPredicate(Root<Product> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
        
        return null;
    }
    
}
