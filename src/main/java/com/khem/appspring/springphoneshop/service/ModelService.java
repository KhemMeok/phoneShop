package com.khem.appspring.springphoneshop.service;

import java.util.List;
import java.util.Map;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
// import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.model.Model;

 

public interface ModelService {
    Model save(ModelDTO dto);
    Model getById(Integer id);
    List<Model> getModel(Map<String,String> param);
}
