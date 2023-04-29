package com.amssolutions.prueba.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.amssolutions.prueba.BaseTest;
import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.api.dto.SimilarProducts;
import com.amssolutions.prueba.exception.ProductNotFoundException;
import com.amssolutions.prueba.exception.WebServiceException;
import com.amssolutions.prueba.service.IProductService;
import com.amssolutions.prueba.service.adapter.IProductWebClientAdapter;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceBusinessException;
import com.amssolutions.prueba.service.mapper.IProductDetailMapper;

@SpringBootTest
class ProductServiceImplTest extends BaseTest {

	@Autowired
	private IProductService productService;
	
	@MockBean
	private IProductWebClientAdapter productWebClientAdapter;
	
	@MockBean
	private IProductDetailMapper productDetailMapper;
	
	@Test
	void testGetSimilarProductDetailsFromProductId() throws Exception {
	
		ProductDetailBusiness expected = mockProductDetailBusinessOther();
		ProductDetail expectedMapped = mockProductDetailOther();
		
		Mockito.when(productWebClientAdapter.getSimilarProducts(PRODUCT_ID)).thenReturn(Arrays.asList(PRODUCT_ID_ALT));
		Mockito.when(productWebClientAdapter.getProductDetails(PRODUCT_ID_ALT)).thenReturn(expected);
		Mockito.when(productDetailMapper.map(any())).thenReturn(expectedMapped);
		
		SimilarProducts result = productService.getSimilarProducts(PRODUCT_ID);
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(expected.getId(), result.get(0).getId());
		assertEquals(expected.getName(), result.get(0).getName());
		assertEquals(expected.getPrice(), result.get(0).getPrice());
		assertEquals(expected.getAvailability(), result.get(0).getAvailability());
	}
	
	@Test
	void testGetSimilarProductDetailsFromProductIdThrowNotFoundException() throws Exception {
		
		Mockito.when(productWebClientAdapter.getSimilarProducts(PRODUCT_ID)).thenThrow(ProductBusinessNotFoundException.class);
		
		assertThrows(ProductNotFoundException.class, () -> productService.getSimilarProducts(PRODUCT_ID));
	}
	
	@Test
	void testGetSimilarProductDetailsFromProductIdThrowWebServiceException() throws Exception {
		
		Mockito.when(productWebClientAdapter.getSimilarProducts(PRODUCT_ID)).thenThrow(WebServiceBusinessException.class);
		
		assertThrows(WebServiceException.class, () -> productService.getSimilarProducts(PRODUCT_ID));
	}
	
}
