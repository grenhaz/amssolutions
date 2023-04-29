package com.amssolutions.prueba.service.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amssolutions.prueba.BaseTest;
import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.mapper.IProductDetailMapper;

@SpringBootTest
class ProductDetailMapperImplTest extends BaseTest {

	@Autowired
	private IProductDetailMapper productDetailMapper;
	
	@Test
	void testMapFromProductDetailBusinessToProductDetail() {
		
		ProductDetailBusiness expected = mockProductDetailBusiness();
		
		ProductDetail result = productDetailMapper.map(expected);
		
		assertNotNull(result);
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getName(), result.getName());
		assertEquals(expected.getPrice(), result.getPrice());
		assertEquals(expected.getAvailability(), result.getAvailability());
	}
	
}
