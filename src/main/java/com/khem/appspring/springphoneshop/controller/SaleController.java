package com.khem.appspring.springphoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khem.appspring.springphoneshop.dto.SaleDTO;
import com.khem.appspring.springphoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
	
	private final SaleService saleService;
	
	@PostMapping
	public ResponseEntity<?> sell(@RequestBody SaleDTO saleDTO){
		
		saleService.sell(saleDTO);
		
		return ResponseEntity.ok().build();

		
	}

}
