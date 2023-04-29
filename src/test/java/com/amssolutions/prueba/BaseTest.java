package com.amssolutions.prueba;

import java.math.BigDecimal;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;

public class BaseTest {
	
	protected static final String PRODUCT_ID = "1";
	protected static final String PRODUCT_ID_ALT = "2";
	protected static final String PRODUCT_NAME = "FAKE";
	protected static final String PRODUCT_NAME_ALT = "FAKE_ALT";
	protected static final BigDecimal PRODUCT_PRICE = new BigDecimal(12);
	protected static final BigDecimal PRODUCT_PRICE_ALT = new BigDecimal(31);
	protected static final boolean PRODUCT_AVAILABILITY = true;
	protected static final boolean PRODUCT_AVAILABILITY_ALT = false;
	
	@MockBean
	private WebClient productWebClient;

	protected ProductDetailBusiness mockProductDetailBusiness() {
		ProductDetailBusiness product = new ProductDetailBusiness();
		
		product.setId(PRODUCT_ID);
		product.setName(PRODUCT_NAME);
		product.setPrice(PRODUCT_PRICE);
		product.setAvailability(PRODUCT_AVAILABILITY);
		
		return product;
	}
	
	protected ProductDetail mockProductDetail() {
		ProductDetail product = new ProductDetail();
		
		product.setId(PRODUCT_ID);
		product.setName(PRODUCT_NAME);
		product.setPrice(PRODUCT_PRICE);
		product.setAvailability(PRODUCT_AVAILABILITY);
		
		return product;
	}
	
	protected ProductDetailBusiness mockProductDetailBusinessOther() {
		ProductDetailBusiness product = new ProductDetailBusiness();
		
		product.setId(PRODUCT_ID_ALT);
		product.setName(PRODUCT_NAME_ALT);
		product.setPrice(PRODUCT_PRICE_ALT);
		product.setAvailability(PRODUCT_AVAILABILITY_ALT);
		
		return product;
	}
	
	protected ProductDetail mockProductDetailOther() {
		ProductDetail product = new ProductDetail();
		
		product.setId(PRODUCT_ID_ALT);
		product.setName(PRODUCT_NAME_ALT);
		product.setPrice(PRODUCT_PRICE_ALT);
		product.setAvailability(PRODUCT_AVAILABILITY_ALT);
		
		return product;
	}
	
}
