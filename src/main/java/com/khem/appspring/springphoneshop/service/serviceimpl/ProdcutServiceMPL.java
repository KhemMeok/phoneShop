package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.Util.PageUtil;
import com.khem.appspring.springphoneshop.dto.ProductDisplayDTO;
import com.khem.appspring.springphoneshop.dto.ProductImportDTO;
import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.exception.ResourceNotFoundException;
import com.khem.appspring.springphoneshop.mapper.ProductImportHistoryMapper;
import com.khem.appspring.springphoneshop.mapper.ProductMapper;
import com.khem.appspring.springphoneshop.model.Color;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.model.ProductImportHistory;
import com.khem.appspring.springphoneshop.repository.ColorRepository;
import com.khem.appspring.springphoneshop.repository.ModelRepository;
import com.khem.appspring.springphoneshop.repository.ProductImportHistoryRepository;
import com.khem.appspring.springphoneshop.repository.ProductRepository;
import com.khem.appspring.springphoneshop.service.ProductService;
import com.khem.appspring.springphoneshop.specification.ProductSpacifition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdcutServiceMPL implements ProductService {

    private final ProductRepository productRepository;
    
    private final ProductImportHistoryRepository importHistoryRepository;
    
    private final ProductMapper productMapper;

	private final ModelRepository modelRepository;

	private final ColorRepository colorRepository;

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
		 
		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not font for id=%ld", id));
	}

	@Override
	public Product setPrice(Long productId, BigDecimal price) {
		//check if product exist, get product
		Product product = getById(productId);
		product.setSalePrice(price);
		
		return productRepository.save(product);
	}

	@Override
	public Page<Product> getProducts(Map<String, String> params) {
		// Specification<Product> productSpcification = (product, query, cb) -> null;
		Pageable pageable = PageUtil.getPageable(params);
		return productRepository.findAll(new ProductSpacifition(), pageable);

	}
	@Override
	public List<ProductDisplayDTO> toProductDisplayDTO(List<Product> products){
		List<ProductDisplayDTO> displayDTOs = new ArrayList<>();

		List<Long> modelId = products.stream().map(p-> p.getModel().getId()).collect(Collectors.toList());
		List<Model> models = modelRepository.findAllById(modelId);
		Map<Long, String> modelMap = models.stream().collect(Collectors.toMap(p->p.getId(),p-> p.getName()));

		List<Long> colorIds = products.stream().map(p-> p.getColor().getId()).collect(Collectors.toList());
		List<Color> colors = colorRepository.findAllById(colorIds);
		Map<Long, String> colorMap = colors.stream().collect(Collectors.toMap( Color::getId ,  Color::getName ));

		for(Product product : products){
			ProductDisplayDTO dto = new ProductDisplayDTO();
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setSalePrice(product.getSalePrice());
			dto.setModel(modelMap.get(product.getModel().getId()));
			dto.setColor(colorMap.get(product.getColor().getId()));
			displayDTOs.add(dto);
		}
		return displayDTOs;
	}

	@Override
	public boolean hasAvialableUint(Long productId, Integer orderUint) {
		
		Product product = getById(productId);
		  	if( product.getAvailableUnit() < orderUint) {
		  		throw new ApiException(HttpStatus.BAD_REQUEST,"product (%s) id=%d not aviable for sale".formatted(product.getName(),productId));
		  	}
	 return true;
	}

	@Override
	public boolean salePriceIsSet(Long productId) {
		Product product = getById(productId);
		if(Objects.isNull(product.getSalePrice())){
			throw new ApiException(HttpStatus.BAD_REQUEST,"product (%s) id=%d not set sale price".formatted(product.getName(),productId));
		}
		return false;
	}
 }
