package com.khem.appspring.springphoneshop.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.PageDTO;
import com.khem.appspring.springphoneshop.dto.PriceDTO;
import com.khem.appspring.springphoneshop.dto.ProductImportDTO;
import com.khem.appspring.springphoneshop.mapper.PageMapper;
import com.khem.appspring.springphoneshop.mapper.ProductMapper;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductImportDTO dto){
        return ResponseEntity.ok(productMapper.toDTO(productService.save(dto)));
    }
    @PutMapping("setPrice/{productId}")
    public ResponseEntity<?> setPrice(@PathVariable("productId") Long productId,@RequestBody PriceDTO priceDTO){
    	Product product = productService.setPrice(productId, priceDTO.getSalePrice());
    	return ResponseEntity.ok(productMapper.toDTO(product));
    }
    @GetMapping
    public ResponseEntity<?> listProducts(@RequestParam Map<String,String> params){
        Page<Product> productsPage = productService.getProducts(params);

        PageDTO pageDTO = PageMapper.INSTANCE.toDTO(productsPage);
        pageDTO.setList(productService.toProductDisplayDTO(productsPage.getContent()));

        
        return ResponseEntity.ok(pageDTO);
    }
   

}
