package com.khem.appspring.springphoneshop.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import com.khem.appspring.springphoneshop.exception.ApiException;
import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.repository.BrandRepository;
import com.khem.appspring.springphoneshop.service.serviceimpl.BrandServiceimpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BrandServiceTest {
	
	@Mock
	private BrandRepository brandRepository;

	private BrandService brandService;

	private Brand brand;

	@BeforeEach // ver_UT meaing befor test any case we should refresh  resoure /state of service 
	public void setup(){
		brandService = new  BrandServiceimpl(brandRepository);
		brand = new Brand(1, "Apple");
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
	}
	// @Test
	public void testServicebrand_old() {
		
		
		
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		//when
		when(brandRepository.save(any(Brand.class))).thenAnswer(new Answer<Brand>() {

			@Override
			public Brand answer(InvocationOnMock invocation) throws Throwable {
				Brand brandEntity = invocation.getArgument(0);
				brandEntity.setId(1);
				return brandEntity;
			}
		});
		
		// when(brandRepository.save(any(Brand.class))).thenAnswer(invocation ->{
			
		// 	Brand brandEntity = invocation.getArgument(0);
		// 	brandEntity.setId(1);
		// 	return brandEntity;
		// });

		Brand brandReturn = brandService.save(brand);
		
		
		//then
		assertEquals("Apple", brandReturn.getName());
		assertEquals(1, brandReturn.getId());

		verify(brandRepository,times(1)).save(brand);
		
	}

	@Test
	public void testServicebrand() {
		
		
		
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		//when
		
		
		// when(brandRepository.save(any(Brand.class))).thenAnswer(invocation ->{
			
		// 	Brand brandEntity = invocation.getArgument(0);
		// 	brandEntity.setId(1);
		// 	return brandEntity;
		// });

		brandService.save(brand);
		
		
		//then
		

		verify(brandRepository,times(1)).save(brand);
		
	}

	@Test
	public void getByIdSucess(){
		//given

		Brand brand = new Brand(1,"Apple");
	 

		//when

		// when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		// Brand brandReturn = brandService.getById(1);

		//then
		Brand brandReturn = brandService.getById(1);
		assertNotNull(brandReturn);
		assertEquals("Apple", brandReturn.getName());
		assertEquals(1, brandReturn.getId());


	}
	@Test
	public void getByIdError() {
		
		//given
		
		
		//when
		when(brandRepository.findById(2)).thenReturn(Optional.empty());
		
		
		
		//then
		 
//		assertThrows(ApiException.class, ()->brandService.getById(2));
		
		assertThatThrownBy(()-> brandService.getById(2))
		.isInstanceOf(ApiException.class)
		.hasMessageStartingWith("brand not found for id=");
		 
	}

	@Test
	public void updateBrand() {

		// given
		Brand brandDB = new Brand(1, "Apple");
		Brand brandUpdate = new Brand(1, "Apple V2");

		// when
		// when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandAfterUpdate = brandService.update(1, brandUpdate);

		// then
		// assertEquals(brandAfterUpdate.getName(), "Apple V2");
		verify(brandRepository, times(1)).save(brandUpdate);

	}

	@Test
	public void testDelete() {
		// given
		Integer brandToDelete = 1;

		// when
		brandService.delete(brandToDelete);

		// then
		verify(brandRepository, times(1)).delete(brand);
	}

	@Test
	public void testListBrand() {
		// given
		List<Brand> brand = List.of(
				new Brand(1, "Apple"),
				new Brand(1, "Samsung"));
		// when
		when(brandRepository.findAll()).thenReturn(brand);
		List<Brand> brandReturn = brandService.findAll();

		// then
		assertEquals(2, brandReturn.size());
		assertEquals("Apple", brandReturn.get(0).getName());
		assertEquals("Samsung", brandReturn.get(1).getName());
	}

}
