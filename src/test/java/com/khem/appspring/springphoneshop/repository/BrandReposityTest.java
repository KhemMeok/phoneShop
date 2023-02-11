package com.khem.appspring.springphoneshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.khem.appspring.springphoneshop.model.Brand;

@DataJpaTest
public class BrandReposityTest {
  
 

    
    @Autowired
    private BrandRepository brandRepository;

     @BeforeEach
     public void setUp(){
         brandRepository.deleteAll();
     }

     @Test
    public void testExistsByname(){

        //geiven
        Brand brand = new Brand();
        brand.setName("Samsung");
        brandRepository.save(brand);

        //when
        boolean existsByName = brandRepository.existsByName("Samsung");
        boolean existsByName2 = brandRepository.existsByName("iphone");



        //then
        assertTrue(existsByName);
        assertFalse(existsByName2);

    }

   @Test
    public void findByIdIn(){
        //given
        Brand brand = new Brand("Iphone");
        Brand brand2  = new Brand("Samsung");
        brandRepository.save(brand);
        brandRepository.save(brand2);

        //when
        List<Brand> brands = brandRepository.findByIdIn(List.of(1L,2L));


        //then
        assertEquals(2, brands.size());
        
        assertEquals(1, brands.get(0).getId());
        assertEquals("Iphone", brands.get(0).getName());

        assertEquals(2, brands.get(1).getId());
        assertEquals("Samsung", brands.get(1).getName());

        List<Brand> findAll = brandRepository.findAll();
        assertEquals(2, findAll.size());

    }

}

