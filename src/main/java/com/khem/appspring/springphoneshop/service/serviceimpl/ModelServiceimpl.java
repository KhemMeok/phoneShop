package com.khem.appspring.springphoneshop.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.repository.ModelRepository;
import com.khem.appspring.springphoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ModelServiceimpl implements ModelService {


    private final ModelRepository modelRepository;

    @Override
    public Model save(Model entiry) {
        
        return modelRepository.save(entiry);
    }

}
