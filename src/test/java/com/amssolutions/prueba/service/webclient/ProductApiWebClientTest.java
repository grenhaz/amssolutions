package com.amssolutions.prueba.service.webclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.amssolutions.prueba.BaseTest;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceBusinessException;
import com.amssolutions.prueba.service.webclient.ProductApiWebClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;

@SpringBootTest
class ProductApiWebClientTest extends BaseTest {

	private ProductApiWebClient productApiWebClient;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public MockWebServer mockBackEnd;
    
    @BeforeEach
    void setUpTest() throws IOException {
    	mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    	productApiWebClient = new ProductApiWebClient(WebClient.builder()
        		.baseUrl(String.format("http://localhost:%s", mockBackEnd.getPort()))
        		.build());
    }
    
    @AfterEach
    void tearDownTest() throws IOException {
        mockBackEnd.shutdown();
    }
	
	@Test
	void testGetSimilarProductsById() throws Exception {
        
        mockBackEnd.enqueue(new MockResponse()
        		.setResponseCode(200)
    			.setBody(objectMapper.writeValueAsString(Arrays.asList(PRODUCT_ID_ALT)))
			    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));
		
		List<String> result = productApiWebClient.getSimilarProducts(PRODUCT_ID);
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(PRODUCT_ID_ALT, result.get(0));
	}
	
	@Test
	void testGetProductDetailById() throws Exception {
		
		ProductDetailBusiness expected = mockProductDetailBusiness();
        
        mockBackEnd.enqueue(new MockResponse()
        		.setResponseCode(200)
    			.setBody(objectMapper.writeValueAsString(expected))
			    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));
		
		ProductDetailBusiness result = productApiWebClient.getProductDetail(PRODUCT_ID);
		
		assertNotNull(result);
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getName(), result.getName());
		assertEquals(expected.getPrice(), result.getPrice());
		assertEquals(expected.getAvailability(), result.getAvailability());
	}
	
	@Test
	void testGetSimilarProductsByIdThrowsNotFoundException() throws Exception {
        
        mockBackEnd.enqueue(new MockResponse()
    			.setResponseCode(404));
		
		assertThrows(ProductBusinessNotFoundException.class, () -> productApiWebClient.getSimilarProducts(PRODUCT_ID));
	}
	
	@Test
	void testGetProductDetailByIdThrowsNotFoundException() throws Exception {
        
        mockBackEnd.enqueue(new MockResponse()
    			.setResponseCode(404));
		
		assertThrows(ProductBusinessNotFoundException.class, () -> productApiWebClient.getProductDetail(PRODUCT_ID));
	}
	
	@Test
	void testExternalApi500ThrowsWebServiceException() throws Exception {
        
        mockBackEnd.enqueue(new MockResponse().setResponseCode(500));
		
		assertThrows(WebServiceBusinessException.class, () -> productApiWebClient.getSimilarProducts(PRODUCT_ID));
		
		mockBackEnd.enqueue(new MockResponse().setResponseCode(500));
		
		assertThrows(WebServiceBusinessException.class, () -> productApiWebClient.getProductDetail(PRODUCT_ID));
	}
	
	@Test
	void testExternalApiDownThrowsWebServiceException() throws Exception {
		
		mockBackEnd.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
		
		assertThrows(WebServiceBusinessException.class, () -> productApiWebClient.getSimilarProducts(PRODUCT_ID));
		
		mockBackEnd.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
		
		assertThrows(WebServiceBusinessException.class, () -> productApiWebClient.getProductDetail(PRODUCT_ID));
	}
	
}
