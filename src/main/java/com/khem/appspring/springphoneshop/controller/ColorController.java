package com.khem.appspring.springphoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.ColorDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.mapper.ColorMapper;
import com.khem.appspring.springphoneshop.model.Color;
import com.khem.appspring.springphoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
public class ColorController {
    
     
    private final ColorService colorService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ColorDTO dto) throws ApiException{
        Color color = ColorMapper.INSTANCE.toColor(dto);
        color = colorService.save(color);
        return ResponseEntity.ok(color);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws ApiException {
        return ResponseEntity.ok(colorService.getById(id));
    }


}
