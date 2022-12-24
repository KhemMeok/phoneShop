package com.khem.appspring.springphoneshop.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import com.khem.appspring.springphoneshop.model.Brand;


@DataJdbcTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testExitByname() {
        // given
        Brand brand = new Brand();
        brand.setName("Samsung");
        brandRepository.save(brand);

        // when
        boolean existsByName = brandRepository.existsByName("Samsung");
        boolean existsByName2 = brandRepository.existsByName("Ipad");

        // then
        // assertEquals(true, existsByName);
        assertTrue(existsByName);
        assertFalse(existsByName2);

    }
   

}
