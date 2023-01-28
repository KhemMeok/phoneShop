package com.khem.appspring.springphoneshop.service;

import java.math.BigDecimal;
import java.util.*;
import org.springframework.data.domain.Page;

import com.khem.appspring.springphoneshop.dto.ProductDisplayDTO;
import com.khem.appspring.springphoneshop.dto.ProductImportDTO;
import com.khem.appspring.springphoneshop.model.Product;

 

public interface ProductService {
    Product save(ProductImportDTO productImportDTO);
    Product getById(Long id);
    Product setPrice(Long productId,BigDecimal price);
    Page<Product> getProducts(Map<String,String> params);
    List<ProductDisplayDTO> toProductDisplayDTO(List<Product> listProduc);
    boolean hasAvialableUint(Long productId,Integer orderUint);
    boolean salePriceIsSet(Long productId);
}
