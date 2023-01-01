package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.ProductImportDTO;
import com.khem.appspring.springphoneshop.exception.ResourceNotFoundException;
import com.khem.appspring.springphoneshop.mapper.ProductImportHistoryMapper;
import com.khem.appspring.springphoneshop.mapper.ProductMapper;
import com.khem.appspring.springphoneshop.model.Color;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.model.ProductImportHistory;
import com.khem.appspring.springphoneshop.repository.ProductImportHistoryRepository;
import com.khem.appspring.springphoneshop.repository.ProductRepository;
import com.khem.appspring.springphoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdcutServiceMPL implements ProductService {

    private final ProductRepository productRepository;
    
    private final ProductImportHistoryRepository importHistoryRepository;
    
    private final ProductMapper productMapper;

    @Override
    public Product save(ProductImportDTO dto)    {	
    	/*
    	 * model 
    	 * color
    	 */
    	Long modelId = dto.getProduct().getModelId();
    	Long colorId = dto.getProduct().getColorId();
    	Optional<Product> exitingProduct = productRepository.findByModelIdAndColorId(modelId,colorId);
    	Product product=null;
    	if(exitingProduct.isPresent()) {
    		/*
    		 * set new available unit in stock 
    		 * get current available unit + new number of unitc
    		 */
  
    	    product = exitingProduct.get();
    		Integer availableUnit = product.getAvailableUnit();
    		Integer importUnit = dto.getImportDetail().getImportUnit();
    		product.setAvailableUnit(availableUnit + importUnit);
    		
    	}else {
    		product = productMapper.toProduct(dto.getProduct());
			product.setAvailableUnit(dto.getImportDetail().getImportUnit());
		}
    	//set product import in history 
    	product = productRepository.save(product);
    	ProductImportHistory entity = ProductImportHistoryMapper.iNSTANCE.toEntity(dto.getImportDetail(), product);
    	importHistoryRepository.save(entity);
    	
        return product;
    }

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not font for id=%ld", id));
	}

}
