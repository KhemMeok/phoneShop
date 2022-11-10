package com.khem.appspring.springphoneshop.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.exception.ResourceNotFoundException;
import com.khem.appspring.springphoneshop.mapper.ModelMapper;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.repository.ModelRepository;
import com.khem.appspring.springphoneshop.service.BrandService;
import com.khem.appspring.springphoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceimpl implements ModelService {

    private final ModelRepository modelRepository;

    private final BrandService brandService;

    @Override
    public Model save(ModelDTO dto) throws ApiException {

        Integer brandId = dto.getBrandDTO().getId();
        brandService.getById(brandId);
        Model model = ModelMapper.INSTANCE.toModel(dto);
        return modelRepository.save(model);
    }

    @Override
    public Model getById(Integer id) throws ApiException {
         return  modelRepository.findById(id)
        //  .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,String.format("model not found for id=%d", id)));
        .orElseThrow(() -> new ResourceNotFoundException("Model",id));
         
    }

}
