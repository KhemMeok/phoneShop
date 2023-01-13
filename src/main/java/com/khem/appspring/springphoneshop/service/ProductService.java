package com.khem.appspring.springphoneshop.service;

import com.khem.appspring.springphoneshop.dto.ProductImportDTO;
import com.khem.appspring.springphoneshop.model.Product;

public interface ProductService {
    Product save(ProductImportDTO productImportDTO);
    Product getById(Long id);
    Product setPrice(Long productId,Double price);
}
