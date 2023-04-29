package com.amssolutions.prueba.service.adapter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.amssolutions.prueba.BaseTest;
import com.amssolutions.prueba.service.adapter.IProductWebClientAdapter;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.webclient.ProductApiWebClient;

@SpringBootTest
class ProductWebClientAdapterImplTest extends BaseTest {
	
	@Autowired
	private IProductWebClientAdapter productWebClientAdapter;
	
	@MockBean
	private ProductApiWebClient productApiWebClient;
	
	@Test
	void testGetSimilarProductsFromProductId() throws Exception {
		
		Mockito.when(productApiWebClient.getSimilarProducts(PRODUCT_ID)).thenReturn(Arrays.asList(PRODUCT_ID_ALT));
		
		List<String> result = productWebClientAdapter.getSimilarProducts(PRODUCT_ID);
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(PRODUCT_ID_ALT, result.get(0));
	}
	
	@Test
	void testProductDetailsFromProductId() throws Exception {
		
		ProductDetailBusiness expected = mockProductDetailBusiness();
		
		Mockito.when(productApiWebClient.getProductDetail(PRODUCT_ID)).thenReturn(expected);
		
		ProductDetailBusiness result = productWebClientAdapter.getProductDetails(PRODUCT_ID);
		
		assertNotNull(result);
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getName(), result.getName());
		assertEquals(expected.getPrice(), result.getPrice());
		assertEquals(expected.getAvailability(), result.getAvailability());
	}
	
}
