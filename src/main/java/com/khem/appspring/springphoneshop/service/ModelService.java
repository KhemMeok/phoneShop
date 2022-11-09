package com.khem.appspring.springphoneshop.service;


import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.model.Model;
 
public interface ModelService {
    Model save(ModelDTO dto);
    
}
