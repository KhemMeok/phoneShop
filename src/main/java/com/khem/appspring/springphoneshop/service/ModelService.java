package com.khem.appspring.springphoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.model.Model;

 

public interface ModelService {
    Model save(ModelDTO dto);
    Model getById(Integer id);
    // List<Model> getModel(Map<String,String> param);
    Page<Model> getModels(Map<String,String> param);
}
