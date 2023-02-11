package com.khem.appspring.springphoneshop.service.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.dto.SaleByDateDTO;
import com.khem.appspring.springphoneshop.model.Product;
import com.khem.appspring.springphoneshop.model.SaleDetail;
import com.khem.appspring.springphoneshop.projection.SaleByDate;
import com.khem.appspring.springphoneshop.repository.ProductRepository;
import com.khem.appspring.springphoneshop.repository.SaleDetailRepository;
import com.khem.appspring.springphoneshop.service.ReportingService;
import com.khem.appspring.springphoneshop.specification.SaleDetailFilter;
import com.khem.appspring.springphoneshop.specification.SaleDetailSpac;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportingServiceIMLP implements ReportingService {
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public List<SaleByDate> getProductBySoldDate(LocalDate soldDate) {
        return saleDetailRepository.findByProduct(soldDate);
    }

    @Override
	public List<SaleByDateDTO> getProductSoldByDateV2(LocalDate soldDate) {
		SaleDetailFilter detailFilter = new SaleDetailFilter();
		detailFilter.setSoldDate(soldDate);

		SaleDetailSpac spec = new SaleDetailSpac(detailFilter);
		List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

		Map<Product, List<SaleDetail>> saleByProductMap = saleDetails.stream()
			.collect(Collectors.groupingBy(SaleDetail::getProduct));

		List<Long> productIds = saleDetails.stream()
				.map(sd -> sd.getProduct().getId())
				.toList();

			List<Product> products = productRepository.findAllById(productIds);
			Map<Long, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		List<SaleByDateDTO> saleByDateDTOs = new ArrayList<>();
		for(Map.Entry<Product, List<SaleDetail>> entry : saleByProductMap.entrySet()) {

			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> saleDetailsList = entry.getValue();
			/*
			Integer totalUnit = saleDetailsList.stream()
				.map(SaleDetail::getUnit)
				.reduce((a,b) -> a+b)
				.get();
			*/

			Long totalUnit = saleDetailsList.stream()
				.collect(Collectors.summingLong(SaleDetail::getUnit));

			Double amount = saleDetailsList.stream()
				.collect(Collectors.summingDouble(sd -> sd.getAmount().doubleValue()));


			SaleByDateDTO dto = new SaleByDateDTO();
			dto.setSoldDate(soldDate);
			dto.setProductId(product.getId());
			dto.setProductName(product.getName());
			dto.setTotalUnit(totalUnit);
			dto.setAmount(amount);
			saleByDateDTOs.add(dto);
		}

		return saleByDateDTOs;
	}

}


