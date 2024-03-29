package com.khem.appspring.springphoneshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.BrandDTO;
import com.khem.appspring.springphoneshop.dto.PageDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.mapper.BrandMapper;
import com.khem.appspring.springphoneshop.mapper.PageMapper;
import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandDTO dto) {

        Brand brand = BrandMapper.INSTANCE.toEntity(dto);
        brand = brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws ApiException {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BrandDTO dto) throws ApiException {
        Brand brand = BrandMapper.INSTANCE.toEntity(dto);
        return ResponseEntity.ok(brandService.update(id, brand));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws ApiException {
        brandService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BrandDTO> brand = brandService.findAll()
                .stream()
                .map(b -> BrandMapper.INSTANCE.toDTO(b)).toList();

        return ResponseEntity.ok(brand);
    }
    @GetMapping("/page")
    public ResponseEntity<?> getBrandList(@RequestParam Map<String,String> params){
        // List<ModelDTO> listDTO = modelService.getModels(params)
        // .stream()
        // .map(a->ModelMapper.INSTANCE.toDTO(a))
        // .toList();
        // return ResponseEntity.ok(listDTO);
        Page<Brand> page = brandService.getBrandPage(params);
        // Page<ModelDTO> page2 =Page.empty();
        //  BeanUtils.copyProperties(page, page2);   
        // PageDTO pageDTO = new PageDTO(page);
        // PageDTO pageDTO = PageMapper.INSTANCE.toDTO(page2);
        PageDTO pageDTO = PageMapper.INSTANCE.toDTO(page);
        pageDTO.setList(page.get().map(BrandMapper.INSTANCE::toDTO).toList());
        return ResponseEntity.ok(pageDTO);
    }

}
