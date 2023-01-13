package com.khem.appspring.springphoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khem.appspring.springphoneshop.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long>{
     
     boolean existsByName(String name);

     List<Brand> findByIdIn(List<Long> ids);

     List<Brand> findByActiveTrue();
}
