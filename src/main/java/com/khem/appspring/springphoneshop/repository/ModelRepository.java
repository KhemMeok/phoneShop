package com.khem.appspring.springphoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.khem.appspring.springphoneshop.model.*;
@Repository
public interface ModelRepository extends JpaRepository<Model, Long> ,JpaSpecificationExecutor<Model> {

}
