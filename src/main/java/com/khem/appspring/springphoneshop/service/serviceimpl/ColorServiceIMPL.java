package com.khem.appspring.springphoneshop.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.exception.ResourceNotFoundException;
import com.khem.appspring.springphoneshop.model.Color;
import com.khem.appspring.springphoneshop.repository.ColorRepository;
import com.khem.appspring.springphoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceIMPL implements ColorService{
	
	@Autowired
	private final ColorRepository colorRepository;
	
	@Override
	public Color save(Color entity) {
		return colorRepository.save(entity);
	}

	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Color not found for id=%d", id));
	}

}
