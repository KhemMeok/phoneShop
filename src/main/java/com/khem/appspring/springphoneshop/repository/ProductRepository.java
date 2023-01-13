package com.khem.appspring.springphoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khem.appspring.springphoneshop.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByModelIdAndColorId(Long modelId,Long colorId );
}