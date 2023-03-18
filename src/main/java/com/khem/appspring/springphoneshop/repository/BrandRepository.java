package com.khem.appspring.springphoneshop.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.khem.appspring.springphoneshop.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> , JpaSpecificationExecutor<Brand>{
     boolean existsByName(String name);

     List<Brand> findByIdIn(List<Long> ids);

     List<Brand> findByActiveTrue(); 
}
