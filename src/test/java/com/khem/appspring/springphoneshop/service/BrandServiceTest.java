package com.khem.appspring.springphoneshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.khem.appspring.springphoneshop.model.Brand;
import com.khem.appspring.springphoneshop.repository.BrandRepository;
import com.khem.appspring.springphoneshop.service.serviceimpl.BrandServiceimpl;
@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	
	@Mock
	private BrandRepository brandRepository;

	private BrandService brandService;
	
	@BeforeEach // ver_UT meaing befor test any case we should refresh  resoure /state of service 
	public void setup(){
		brandService = new  BrandServiceimpl(brandRepository);
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

		Brand brandReturn = brandService.save(brand);
		
		
		//then
		

		verify(brandRepository,times(1)).save(brand);
		
	}

	@Test
	public void getById(){
		//given

		Brand brand = new Brand(1,"Apple");
	 

		//when

		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1);

		//then

		assertNotNull(brandReturn);
		assertEquals("Apple", brandReturn.getName());
		assertEquals(1, brandReturn.getId());


	}

}
