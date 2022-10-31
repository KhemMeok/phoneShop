package com.khem.appspring.springphoneshop.controller;

 

import java.util.List;
 
import java.util.stream.Collectors;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.mapper.BrandMapper;
import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandDTO dto) {

        Brand brand = BrandMapper.toBrand(dto);
        brand = brandService.save(brand);
        return ResponseEntity.ok(brand);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(brandService.getById(id));
    } 
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody BrandDTO dto){
    
        return ResponseEntity.ok(brandService.update(id,dto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        brandService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<BrandDTO> brands = brandService.findAll()
        .stream()
               .map(b->BrandMapper.toBrandDTO(b)).toList();
             //  .collect(Collectors.toList());
        return ResponseEntity.ok(brands);
    } 

}
