package com.khem.appspring.springphoneshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.khem.appspring.springphoneshop.dto.SaleByDateDTO;
import com.khem.appspring.springphoneshop.model.SaleDetail;
import com.khem.appspring.springphoneshop.projection.SaleByDate;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail>{

    @Query(value = "SELECT DATE(S.sold_date) AS SoldDate, P.id as ProductId,P.name as ProductName, sum(sd.unit) as Total, sum(sd.amount * sd.unit) as  Amount FROM sales S INNER JOIN sale_details SD ON SD.sale_id = S.id INNER JOIN products P ON P.id = SD.product_id WHERE DATE(S.sold_date) = :soldDate GROUP BY DATE(S.sold_date), P.id,P.name", nativeQuery = true)
    List<SaleByDate> findByProduct(@Param("soldDate") LocalDate soldDate);

}
