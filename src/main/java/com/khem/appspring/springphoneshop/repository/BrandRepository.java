package com.khem.appspring.springphoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khem.appspring.springphoneshop.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer>{
    
}
