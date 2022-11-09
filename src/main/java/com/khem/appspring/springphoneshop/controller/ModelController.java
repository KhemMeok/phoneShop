package com.khem.appspring.springphoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.ModelDTO;
import com.khem.appspring.springphoneshop.mapper.ModelMapper;
import com.khem.appspring.springphoneshop.model.Model;
// import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto) {
        Model model = modelService.save(dto);
        // Model model = ModelMapper.INSTANCE.toModel(dto);
        ModelDTO modelDTO = ModelMapper.INSTANCE.toDTO(model);
        return ResponseEntity.ok(modelDTO);
    }
}
