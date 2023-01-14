package com.khem.appspring.springphoneshop.service;

import java.util.*;
import org.springframework.data.domain.Page;

import com.khem.appspring.springphoneshop.dto.ProductDisplayDTO;
import com.khem.appspring.springphoneshop.dto.ProductImportDTO;
import com.khem.appspring.springphoneshop.model.Product;

 

public interface ProductService {
    Product save(ProductImportDTO productImportDTO);
    Product getById(Long id);
    Product setPrice(Long productId,Double price);
    Page<Product> getProducts(Map<String,String> params);
    List<ProductDisplayDTO> toProductDisplayDTO(List<Product> listProduc);
}
