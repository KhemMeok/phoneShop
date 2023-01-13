package com.khem.appspring.springphoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.khem.appspring.springphoneshop.model.Model;

 

public interface ModelService {
    Model save(Model entity);
    Model getById(Long id);
    // List<Model> getModel(Map<String,String> param);
    Page<Model> getModels(Map<String,String> param);
}
