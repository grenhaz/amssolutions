package com.amssolutions.prueba;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;

import reactor.core.publisher.Mono;

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
	
	protected void mockWebClient(final List<String> response) {
	    final RequestHeadersUriSpec uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
	    final RequestHeadersSpec headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
	    final ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

	    when(productWebClient.get()).thenReturn(uriSpecMock);
	    when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.header(notNull(), notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.headers(notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
	    when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String[]>>notNull()))
	            .thenReturn(Mono.just(response.toArray(new String[response.size()])));
	}
	
	protected void mockWebClient(final ProductDetailBusiness response) {
	    final RequestHeadersUriSpec uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
	    final RequestHeadersSpec headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
	    final ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

	    when(productWebClient.get()).thenReturn(uriSpecMock);
	    when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.header(notNull(), notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.headers(notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
	    when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<ProductDetailBusiness>>notNull()))
	            .thenReturn(Mono.just(response));
	}
}
