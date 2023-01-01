package com.khem.appspring.springphoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khem.appspring.springphoneshop.model.Color;

public interface ColorRepository extends JpaRepository<Color,Long>{
    
}
