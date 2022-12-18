package com.khem.appspring.springphoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khem.appspring.springphoneshop.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
