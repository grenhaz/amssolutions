package com.amssolutions.prueba.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.amssolutions.prueba.BaseTest;
import com.amssolutions.prueba.PruebaApplication;
import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.api.dto.SimilarProducts;
import com.amssolutions.prueba.exception.ProductNotFoundException;
import com.amssolutions.prueba.exception.WebServiceException;
import com.amssolutions.prueba.service.IProductService;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = PruebaApplication.class)
@AutoConfigureMockMvc
class ProductControllerTest extends BaseTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private IProductService productService;
	
	@Test
	void testEndpointProductSimilarByProductId() throws Exception {
		
		SimilarProducts source = new SimilarProducts();
		ProductDetail expected = mockProductDetailOther();
		source.add(expected);
		
		Mockito.when(productService.getSimilarProducts(PRODUCT_ID)).thenReturn(source);
		
		mvc.perform(get(ProductController.ENDPOINT_PRODUCT_SIMILAR.replace("{productId}", PRODUCT_ID))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(expected.getId())))
				.andExpect(jsonPath("$[0].name", is(expected.getName())))
				.andExpect(jsonPath("$[0].price", is(expected.getPrice().intValue())))
				.andExpect(jsonPath("$[0].availability", is(expected.getAvailability())));
	}
	
	@Test
	void testEndpointProductSimilarByProductIdResponseNotFound() throws Exception {
		
		Mockito.when(productService.getSimilarProducts(PRODUCT_ID)).thenThrow(ProductNotFoundException.class);
		
		mvc.perform(get(ProductController.ENDPOINT_PRODUCT_SIMILAR.replace("{productId}", PRODUCT_ID))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void testEndpointProductSimilarByProductIdResponseError() throws Exception {
		
		Mockito.when(productService.getSimilarProducts(PRODUCT_ID)).thenThrow(WebServiceException.class);
		
		mvc.perform(get(ProductController.ENDPOINT_PRODUCT_SIMILAR.replace("{productId}", PRODUCT_ID))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}
	
	@Test
	void testEndpointProductSimilarByProductIdResponseBadRequest() throws Exception {
		
		Mockito.when(productService.getSimilarProducts(PRODUCT_ID)).thenThrow(WebServiceException.class);
		
		mvc.perform(get(ProductController.ENDPOINT_PRODUCT_SIMILAR.replace("{productId}", StringUtils.SPACE))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
