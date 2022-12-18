package com.khem.appspring.springphoneshop.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.repository.ProductRepository;
import com.khem.appspring.springphoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdcutServiceMPL implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

}
