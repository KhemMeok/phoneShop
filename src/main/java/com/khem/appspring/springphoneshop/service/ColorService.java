package com.khem.appspring.springphoneshop.service;

import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.model.Color;
@Service
public interface ColorService {
	
	Color save(Color entity);
	
	Color getById(Long id);
	

}
