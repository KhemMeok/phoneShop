package com.khem.appspring.springphoneshop.specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.khem.appspring.springphoneshop.model.Sale;
import com.khem.appspring.springphoneshop.model.SaleDetail;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class SaleDetailSpac implements Specification<SaleDetail> {
    private final SaleDetailFilter detailFilter;
 

	List<Predicate> predicates = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(detailFilter.getSoldDate() != null) {
			Join<SaleDetail, Sale> sale = saleDetail.join("sale");
			//Predicate soldDate = sale.get("soldDate").in(detailFilter.getSoldDate());
			LocalDate date = detailFilter.getSoldDate();

			LocalDateTime startDateTime = date.atStartOfDay();
			LocalDateTime endDateTime = date.atTime(LocalTime.MAX);

			Predicate soldDate = cb.between(sale.get("soldDate"), startDateTime, endDateTime);

			predicates.add(soldDate);
		}
		Predicate[] predicateArr = predicates.toArray(Predicate[]::new);
		return cb.and(predicateArr);
	}
}
