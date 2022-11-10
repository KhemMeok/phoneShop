package com.khem.appspring.springphoneshop.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
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
    public Model save(ModelDTO dto) {

        Integer brandId = dto.getBrandDTO().getId();
        brandService.getById(brandId);
        Model model = ModelMapper.INSTANCE.toModel(dto);
        return modelRepository.save(model);
    }

}
