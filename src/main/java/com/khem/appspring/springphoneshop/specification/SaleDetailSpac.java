package com.khem.appspring.springphoneshop.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.khem.appspring.springphoneshop.model.Sale;
import com.khem.appspring.springphoneshop.model.SaleDetail;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class SaleDetailSpac implements Specification<SaleDetail> {
    private final SaleDetailFilter detailFilter;

    List<Predicate> predicate = new ArrayList<>();

    @Override
    @Nullable
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(detailFilter.getSoldDate() != null){
            Join<SaleDetail,Sale> sale = saleDetail.join("sale");
            Predicate soldDate = sale.get("soldDate").in(detailFilter.getSoldDate());
            predicate.add(soldDate);
        }
        Predicate[] predicatesArr = predicate.toArray(Predicate[]::new);
       
        return  criteriaBuilder.and(predicatesArr); 
    }
    
}
