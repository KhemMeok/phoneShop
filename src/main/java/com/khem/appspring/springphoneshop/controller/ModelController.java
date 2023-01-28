package com.khem.appspring.springphoneshop.controller;



 
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.dto.PageDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.mapper.ModelEntityMapper;
import com.khem.appspring.springphoneshop.mapper.PageMapper;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    
    private final ModelEntityMapper modelEntityMapper;
    

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto) throws ApiException {
        // Model model = ModelMapper.INSTANCE.toModel(dto);
        Model model = modelEntityMapper.toModel(dto);
        
        model = modelService.save(model);
        // Model model = ModelMapper.INSTANCE.toModel(dto);
        ModelDTO modelDTO = ModelEntityMapper.INSTANCE.toDTO(model);
        return ResponseEntity.ok(modelDTO);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws ApiException {

        Model model = modelService.getById(id);

        return ResponseEntity.ok(ModelEntityMapper.INSTANCE.toDTO(model));
    }
    @GetMapping
    public ResponseEntity<?> getModelList(@RequestParam Map<String,String> params){
        // List<ModelDTO> listDTO = modelService.getModels(params)
        // .stream()
        // .map(a->ModelMapper.INSTANCE.toDTO(a))
        // .toList();
        // return ResponseEntity.ok(listDTO);
        Page<Model> page = modelService.getModels(params);
        // Page<ModelDTO> page2 =Page.empty();
        //  BeanUtils.copyProperties(page, page2);   
        // PageDTO pageDTO = new PageDTO(page);
        // PageDTO pageDTO = PageMapper.INSTANCE.toDTO(page2);
        PageDTO pageDTO = PageMapper.INSTANCE.toDTO(page);
        pageDTO.setList(page.get().map(ModelEntityMapper.INSTANCE::toDTO).toList());
        return ResponseEntity.ok(pageDTO);
    }
}
