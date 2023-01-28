package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.ProductOrderDTO;
import com.khem.appspring.springphoneshop.dto.SaleDTO;
import com.khem.appspring.springphoneshop.mapper.SaleMapper;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.model.Sale;
import com.khem.appspring.springphoneshop.model.SaleDetail;
import com.khem.appspring.springphoneshop.repository.ProductRepository;
import com.khem.appspring.springphoneshop.repository.SaleDetailRepository;
import com.khem.appspring.springphoneshop.repository.SaleRepository;
import com.khem.appspring.springphoneshop.service.ProductService;
import com.khem.appspring.springphoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceIMPL implements SaleService{

	
	private final SaleRepository saleRepository;
	
	private final SaleDetailRepository saleDetailRepository;
	
	private final ProductService productService;
	
	private final SaleMapper saleMapper;
	
	private final ProductRepository productRepository;
	
	@Override
	public void sell(SaleDTO saleDTO) {
		List<ProductOrderDTO> productOrderDTO=saleDTO.getProducts();
		for(ProductOrderDTO orderDTO : productOrderDTO) {
			productService.hasAvialableUint(orderDTO.getProductId(), orderDTO.getUnit());

			productService.salePriceIsSet(orderDTO.getProductId());

		}
		
		List<Long> productIds = productOrderDTO.stream()
				.map(ProductOrderDTO::getProductId)
				.toList();
		
		  Map<Long, Product> productMap = productRepository.findAllById(productIds)
		  .stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		 
		
		//sale sale
		Sale sale = saleMapper.toSale(saleDTO);
		saleRepository.save(sale);
		
		//sale sale_detail
		for(ProductOrderDTO orderDTO : productOrderDTO) {
			Product product = productMap.get(orderDTO.getProductId());
		SaleDetail saleDetail = saleMapper.toSaleDetail(orderDTO, sale, product.getSalePrice());
		saleDetailRepository.save(saleDetail);
		
		
		//update stock
		product.setAvailableUnit(product.getAvailableUnit() - orderDTO.getUnit());
		productRepository.save(product);
		
		}
	}

}

